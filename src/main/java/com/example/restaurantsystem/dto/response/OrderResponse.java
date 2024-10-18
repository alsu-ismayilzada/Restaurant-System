package com.example.restaurantsystem.dto.response;

import com.example.restaurantsystem.entity.Item;
import com.example.restaurantsystem.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponse {
        Item item;
        User user;
        LocalDateTime date;
        Double bill;

}
