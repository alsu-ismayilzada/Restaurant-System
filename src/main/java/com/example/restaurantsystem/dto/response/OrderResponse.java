package com.example.restaurantsystem.dto.response;

import com.example.restaurantsystem.entity.Item;
import com.example.restaurantsystem.entity.User;
import com.example.restaurantsystem.enums.OrderState;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
        List<Item> item;
        User user;
        LocalDateTime date;
        Double bill;
        OrderState status;

}
