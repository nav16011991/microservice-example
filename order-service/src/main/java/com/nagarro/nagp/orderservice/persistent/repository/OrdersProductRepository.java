package com.nagarro.nagp.orderservice.persistent.repository;

import com.nagarro.nagp.orderservice.persistent.model.OrdersProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersProductRepository extends JpaRepository<OrdersProductEntity, Long> {
}
