package com.example.restaurantsystem.mapper;

import com.example.restaurantsystem.dto.OrderDto;
import com.example.restaurantsystem.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto toOrderDto(Order order);
    Order toOrderEntity(OrderDto order);
}
