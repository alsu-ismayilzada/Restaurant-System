package com.example.restaurantsystem.dto.response;

import com.example.restaurantsystem.entity.Table;
import com.example.restaurantsystem.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationResponse{

    User user;
    LocalDateTime date;
    Table table;
    Double price;
}
