package com.example.restaurantsystem.dto.request;

import com.example.restaurantsystem.entity.Table;
import com.example.restaurantsystem.entity.User;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class ReservationRequest {

    Long user;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime date;
    Long table;
    Double price;
}
