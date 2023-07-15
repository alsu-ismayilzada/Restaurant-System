package com.example.restaurantsystem.mappertest;

import com.example.restaurantsystem.dto.OrderDto;
import com.example.restaurantsystem.entity.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class OrderMapperTest {

    private final OrderMapper orderMapper = new OrderMapperImpl();
    @Test
    void toOrderDto() {
        //given
        Order order = new Order(null,null,null, LocalDateTime.of(2002,12,12,12,45,45),10.0);
        OrderDto expected = new OrderDto(null,null, LocalDateTime.of(2002,12,12,12,45,45),10.0);
        //when
        OrderDto actual = orderMapper.toOrderDto(order);
        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void toOrderEntity() {
        //given
        OrderDto orderDto = new OrderDto(null,null, LocalDateTime.now(),10.0);
        Order expected = new Order(1,null,null, LocalDateTime.now(),10.0);
        //when
        Order actual = orderMapper.toOrderEntity(orderDto);
        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}