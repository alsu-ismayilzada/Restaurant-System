package com.example.restaurantsystem.dto.request;

import com.example.restaurantsystem.entity.Item;
import com.example.restaurantsystem.entity.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderRequest {
    List<Long> item;
    Long user;
    LocalDateTime date;
    Double bill;
}
