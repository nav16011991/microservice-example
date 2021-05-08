package com.nagarro.nagp.orderservice.service;

import com.google.gson.Gson;
import com.nagarro.nagp.orderservice.persistent.model.OrdersEntity;
import com.nagarro.nagp.orderservice.persistent.model.OrdersProductEntity;
import com.nagarro.nagp.orderservice.persistent.repository.OrdersProductRepository;
import com.nagarro.nagp.orderservice.persistent.repository.OrdersRepository;
import com.nagarro.nagp.orderservice.service.model.OrdersDTO;
import com.nagarro.nagp.orderservice.service.model.OrdersProductDTO;
import com.nagarro.nagp.orderservice.service.model.UserDTO;
import com.nagarro.nagp.productservice.service.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrdersProductRepository ordersProductRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;



    public List<OrdersDTO> getAll() {
        return ordersRepository.findAll().stream().map(this::covnertToDTO).collect(Collectors.toList());
    }

    private OrdersDTO covnertToDTO(OrdersEntity ordersEntity) {
        if(ObjectUtils.isEmpty(ordersEntity)) {
            return null;
        }

        List<OrdersProductDTO> ordersProductList = ordersEntity.getOrdersProductEntityList().stream().map(this::convertToDTO).collect(Collectors.toList());
        return OrdersDTO.builder()
                .id(ordersEntity.getId())
                .user(UserDTO.builder().username(ordersEntity.getUsername()).build())
                .ordersProductDTOList(ordersProductList)
                .totalAmount(ordersEntity.getTotalAmount())
                .build();
    }

    private OrdersProductDTO convertToDTO(OrdersProductEntity ordersProductEntity) {
        return OrdersProductDTO.builder()
                .id(ordersProductEntity.getId())
                .product(getProduct(ordersProductEntity.getProductId()))
                .quantity(ordersProductEntity.getQuantity())
                .build();
    }

    private ProductDTO getProduct(Long productId) {
        try {
            List<ServiceInstance> instances = discoveryClient.getInstances("PRODUCT-SERVICE");
            String productServiceUrl = instances.get(0).getUri()+"/product/"+ productId;
            ResponseEntity<String> response = restTemplate.exchange(productServiceUrl,
                    HttpMethod.GET,
                    null,
                    String.class);
            return new Gson().fromJson(response.getBody(), ProductDTO.class);
        } catch (RestClientException ex) {
            throw ex;
        }
    }

    private UserDTO getUser(Long userId) {
        try {
            CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");

            return circuitBreaker.run(() -> {
                List<ServiceInstance> instances = discoveryClient.getInstances("USER-SERVICE");
                String userServiceUrl = instances.get(0).getUri()+"/user/"+ userId;
                ResponseEntity<String> response = restTemplate.exchange(userServiceUrl,
                        HttpMethod.GET,
                        null,
                        String.class);
                return new Gson().fromJson(response.getBody(), UserDTO.class);
            }, throwable -> {
                return UserDTO.builder().username("Anonymous").build();
            });

        } catch (RestClientException ex) {
            throw ex;
        }
    }


    public OrdersDTO get(String id) {
        return covnertToDTO(ordersRepository.findById(Long.parseLong(id)).orElse(null));
    }

    public void save(OrdersDTO ordersDTO) {
        Map<Long, ProductDTO> productDTOMap = ordersDTO.getOrdersProductDTOList().stream()
                .map(ordersProduct -> this.getProduct(ordersProduct.getProduct().getId()))
                .collect(Collectors.toMap(ProductDTO::getId,
                        Function.identity()));
        UserDTO userDTO = getUser(Long.parseLong(ordersDTO.getUser().getId()));
        OrdersEntity ordersEntity = OrdersEntity.builder()
                .totalAmount(ordersDTO.getOrdersProductDTOList().stream().map(ordersProductDTO -> ordersProductDTO.getQuantity() * productDTOMap.get(ordersProductDTO.getProduct().getId()).getPrice()).collect(Collectors.summingDouble(Double::longValue)))
                .username(userDTO.getUsername())
                .build();

        ordersRepository.save(ordersEntity);

        List<OrdersProductEntity> ordersProductList = ordersDTO.getOrdersProductDTOList().stream().map(product ->
                OrdersProductEntity.builder()
                        .productId(product.getProduct().getId())
                        .order(ordersEntity)
                        .quantity(product.getQuantity())
                        .build()
        ).collect(Collectors.toList());

        ordersProductRepository.saveAll(ordersProductList);
    }


}
