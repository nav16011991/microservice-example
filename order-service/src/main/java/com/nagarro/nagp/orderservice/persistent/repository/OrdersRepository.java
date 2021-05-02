package com.nagarro.nagp.orderservice.persistent.repository;

import com.nagarro.nagp.orderservice.persistent.model.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, Long> {
}
