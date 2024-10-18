package com.example.restaurantsystem.dto.request;

import com.example.restaurantsystem.entity.Table;
import com.example.restaurantsystem.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationRequest {
    User user;
    LocalDateTime date;
    Table table;
    Double price;
}
