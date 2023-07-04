package com.example.restaurantsystem.management;

import com.example.restaurantsystem.dto.OrderDto;
import com.example.restaurantsystem.mapper.OrderMapper;
import com.example.restaurantsystem.repository.OrderRepository;
import com.example.restaurantsystem.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class OrderManagement implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public void addOrder(OrderDto order) {
        orderRepository.save(orderMapper.toOrderEntity(order));
    }

    @Override
    public void rejectById(Integer id) {
        orderRepository.deleteById(id);
    }

    @Override
    public OrderDto getById(Integer id) {
        return orderRepository.findById(id)
                .stream().map(orderMapper::toOrderDto)
                .findFirst().get();
    }

    @Override
    public List<OrderDto> getAll() {
        return orderRepository.findAll()
                .stream().map(orderMapper::toOrderDto)
                .toList();
    }
}
