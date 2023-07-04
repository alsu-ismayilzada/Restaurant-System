package com.example.restaurantsystem.controller;

import com.example.restaurantsystem.dto.OrderDto;
import com.example.restaurantsystem.management.OrderManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderManager orderManager;

    public OrderController(OrderManager orderManager) {
        this.orderManager = orderManager;
    }
    @PostMapping()
    public void addOrder(@RequestBody OrderDto order){
        orderManager.addOrder(order);
    }
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id){
        orderManager.deleteById(id);
    }
    @GetMapping("{id}")
    public OrderDto getById(@PathVariable Integer id){
        return orderManager.getById(id);
    }
    @GetMapping()
    public List<OrderDto> getAll(){
        return orderManager.getAll();
    }
}
