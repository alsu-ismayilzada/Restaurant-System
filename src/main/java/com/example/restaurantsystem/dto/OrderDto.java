package com.example.restaurantsystem.dto;

import com.example.restaurantsystem.entity.Item;
import com.example.restaurantsystem.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public record OrderDto (
        Item item,
        User user,
        LocalDateTime date,
        Double bill
){
}
