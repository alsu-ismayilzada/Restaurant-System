package com.example.restaurantsystem.service.impl;

import com.example.restaurantsystem.config.StatemachineEngine;
import com.example.restaurantsystem.dto.response.OrderResponse;
import com.example.restaurantsystem.dto.request.OrderRequest;
import com.example.restaurantsystem.entity.Item;
import com.example.restaurantsystem.entity.Order;
import com.example.restaurantsystem.entity.OrderStatusForOrder;
import com.example.restaurantsystem.enums.OrderEvent;
import com.example.restaurantsystem.enums.OrderState;
import com.example.restaurantsystem.mapper.OrderMapper;
import com.example.restaurantsystem.repository.OrderRepository;
import com.example.restaurantsystem.service.ItemInfoService;
import com.example.restaurantsystem.service.ItemService;
import com.example.restaurantsystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final StatemachineEngine stateMachineService;
    private final ItemService itemService;
    private final ItemInfoService itemInfoService;

    @Transactional
    @Override
    public OrderResponse addOrder(OrderRequest request) {
        var order = orderMapper.toOrderEntity(request);
        var itemInfos = order.getItemInfos();

        itemInfos.forEach(itemInfo -> {
            itemInfo.setItem(itemService.findById(itemInfo.getItem().getId()));
            itemInfo.setOrder(order);// Ensure item is fetched
            itemInfoService.saveItemInfo(itemInfo); // Save each ItemInfo
        });

        Double totalBill = itemInfos.stream()
                .mapToDouble(itemInfo -> {
                    Item item = itemInfo.getItem();
                    return (item.getPrice() != null ? item.getPrice() : 0.0) * itemInfo.getCount();
                })
                .sum();

        order.setBill(totalBill);
        var orderEntity = orderRepository.save(order);
        stateMachineService.sendMessage(orderEntity.getId(), OrderEvent.CREATE);

        return orderMapper.toOrderDto(orderEntity);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public OrderResponse getById(Long id) {
        return orderMapper.toOrderDto(findById(id));
    }

    @Override
    public List<OrderResponse> getAll(int page, int count) {
        Page<Order> all = orderRepository.findAll(PageRequest.of(page,count));

        return all.getContent()
                .stream().map(orderMapper::toOrderDto)
                .toList();
    }

    @Override
    public OrderResponse updateById(Long id, OrderRequest request) {
        var order = findById(id);
        orderMapper.updateOrder(order, request);
        return orderMapper.toOrderDto(order);
    }

    public Order findById(Long id){
        return orderRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Order not found"));
    }

    @Override
    public OrderResponse processOrder(Long id) {
        stateMachineService.sendMessage(id, OrderEvent.PROCESS);
        return orderMapper.toOrderDto(findById(id));
    }

    @Override
    public OrderResponse shipOrder(Long id) {
        stateMachineService.sendMessage(id,OrderEvent.SHIP);
        return orderMapper.toOrderDto(findById(id));
    }

    @Override
    public OrderResponse deliverOrder(Long id) {
        stateMachineService.sendMessage(id,OrderEvent.DELIVER);
        return orderMapper.toOrderDto(findById(id));
    }

    @Override
    public OrderResponse cancelOrder(Long id) {
        var order = findById(id);
        var lastState = order.getOrderStatusForOrders().stream()
                        .max(Comparator.comparing(OrderStatusForOrder::getRegDate));
        lastState.ifPresent(state ->{
            if(!(state.getState().equals(OrderState.CREATED) || state.getState().equals(OrderState.PROCESSING))){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Only orders in CREATED or PROCESSING status can be canceled.");
            }
        });
        stateMachineService.sendMessage(id,OrderEvent.CANCEL);
        return orderMapper.toOrderDto(findById(id));
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
