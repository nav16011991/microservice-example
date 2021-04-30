package com.nagarro.nagp.productservice.persistent.repository;

import com.nagarro.nagp.productservice.persistent.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
