package com.example.restaurantsystem.mapper;

import com.example.restaurantsystem.dto.response.OrderResponse;
import com.example.restaurantsystem.dto.request.OrderRequest;
import com.example.restaurantsystem.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderResponse toOrderDto(Order order);
    Order toOrderEntity(OrderRequest order);
}
