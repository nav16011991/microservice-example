package com.nagarro.nagp.orderservice.controller;

import com.nagarro.nagp.orderservice.service.OrdersService;
import com.nagarro.nagp.orderservice.service.model.OrdersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping
    public List<OrdersDTO> getAll() {
        return ordersService.getAll();
    }

    @GetMapping("/{id}")
    public OrdersDTO get(@PathVariable("id") String id) {
        return ordersService.get(id);
    }

    @PostMapping
    public void save(@RequestBody OrdersDTO ordersDTO) {
         ordersService.save(ordersDTO);
    }
}
