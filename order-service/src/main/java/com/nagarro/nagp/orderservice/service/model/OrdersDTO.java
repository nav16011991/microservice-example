package com.nagarro.nagp.orderservice.service.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrdersDTO {

    private Long id;

    private UserDTO user;

    private Double totalAmount;

    private List<OrdersProductDTO> ordersProductDTOList;
}
