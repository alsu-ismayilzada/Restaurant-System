package com.example.restaurantsystem.service;

import com.example.restaurantsystem.dto.OrderDto;

import java.util.List;

public interface OrderService {

    void addOrder(OrderDto order);
    void deleteById(Integer id);
    OrderDto getById(Integer id);
    List<OrderDto> getAll();
}
