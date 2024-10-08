package com.example.restaurantsystem.dto;

import com.example.restaurantsystem.entity.Item;
import com.example.restaurantsystem.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {
        Item item;
        User user;
        LocalDateTime date;
        Double bill;

}
