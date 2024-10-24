package com.example.restaurantsystem.service;

import com.example.restaurantsystem.dto.response.OrderResponse;
import com.example.restaurantsystem.dto.request.OrderRequest;

import java.util.List;

public interface OrderService {

    OrderResponse addOrder(OrderRequest order);

    void deleteById(Long id);

    OrderResponse getById(Long id);

    List<OrderResponse> getAll(int page, int count);

    OrderResponse updateById(Long id, OrderRequest order);

    OrderResponse processOrder(Long id);

    OrderResponse shipOrder(Long id);

    OrderResponse deliverOrder(Long id);

    OrderResponse cancelOrder(Long id);

    void deleteOrder(Long id);
}
