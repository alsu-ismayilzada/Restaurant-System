package com.example.restaurantsystem.service;

import com.example.restaurantsystem.entity.Order;

import java.util.List;

public interface OrderService {

    void addOrder(Order order);
    void rejectById(Integer id);
    Order getById(Integer id);
    List<Order> getAll();
}
