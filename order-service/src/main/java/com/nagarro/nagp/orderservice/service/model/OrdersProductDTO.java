package com.nagarro.nagp.orderservice.service.model;

import com.nagarro.nagp.productservice.service.model.ProductDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrdersProductDTO {

    private Long id;

    private OrdersDTO order;

    private ProductDTO product;

    private Long quantity;
}
