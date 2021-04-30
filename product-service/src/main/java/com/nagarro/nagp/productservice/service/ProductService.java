package com.nagarro.nagp.productservice.service;

import com.nagarro.nagp.productservice.persistent.repository.ProductRepository;
import com.nagarro.nagp.productservice.service.mapper.ProductMapper;
import com.nagarro.nagp.productservice.service.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAll() {
        return productRepository.findAll().stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }

    public ProductDTO get(String id) {
        return ProductMapper.toDTO(productRepository.findById(Long.parseLong(id)).orElse(null));
    }
}
