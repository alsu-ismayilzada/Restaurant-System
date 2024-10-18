package com.example.restaurantsystem.service.impl;

import com.example.restaurantsystem.entity.Order;
import com.example.restaurantsystem.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class OrderManagerTest {

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderManager orderManager;

    @Test
    void addOrder() {
        //given

        //when

        //then
    }

    @Test
    void deleteById() {
    }

    @Test
    void getById() {
    }

    @Test
    void getAll() {
    }
}