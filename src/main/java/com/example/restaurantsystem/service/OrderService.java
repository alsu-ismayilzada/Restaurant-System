package com.example.restaurantsystem.service;

import com.example.restaurantsystem.dto.OrderDto;
import com.example.restaurantsystem.entity.Order;

import java.util.List;

public interface OrderService {

    void addOrder(OrderDto order);
    void rejectById(Integer id);
    OrderDto getById(Integer id);
    List<OrderDto> getAll();
}
