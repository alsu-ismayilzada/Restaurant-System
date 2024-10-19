package com.example.restaurantsystem.mapper;

import com.example.restaurantsystem.dto.response.OrderResponse;
import com.example.restaurantsystem.dto.request.OrderRequest;
import com.example.restaurantsystem.entity.Order;
import com.example.restaurantsystem.service.ItemService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = ItemService.class)
public interface OrderMapper {

    OrderResponse toOrderDto(Order order);
    Order toOrderEntity(OrderRequest order);
    void updateOrder(@MappingTarget Order order, OrderRequest request);
}
