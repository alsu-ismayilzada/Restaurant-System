package com.example.restaurantsystem.service;

import com.example.restaurantsystem.dto.response.OrderResponse;
import com.example.restaurantsystem.dto.request.OrderRequest;

import java.util.List;

public interface OrderService {

    void addOrder(OrderRequest order);
    void deleteById(Integer id);
    OrderResponse getById(Integer id);
    List<OrderResponse> getAll(int page, int count);
    OrderResponse updateById(Integer id, OrderRequest order);
}
