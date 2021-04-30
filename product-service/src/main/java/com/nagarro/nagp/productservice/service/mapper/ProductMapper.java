package com.nagarro.nagp.productservice.service.mapper;

import com.nagarro.nagp.productservice.persistent.model.ProductEntity;
import com.nagarro.nagp.productservice.service.model.ProductDTO;
import org.springframework.util.ObjectUtils;

public class ProductMapper {
    public static ProductDTO toDTO(ProductEntity productEntity) {
        if(ObjectUtils.isEmpty(productEntity)) {
            return null;
        }
        return ProductDTO.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .build();
    }
}
