package com.example.restaurantsystem.dto;

import com.example.restaurantsystem.entity.Table;
import com.example.restaurantsystem.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public record ReservationDto(

        User user,
        LocalDateTime date,
        Table table,
        Double price
) {
}
