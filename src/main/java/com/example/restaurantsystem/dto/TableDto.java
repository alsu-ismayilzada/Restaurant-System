package com.example.restaurantsystem.dto;

import com.example.restaurantsystem.entity.repository.TableStatus;

public record TableDto(

        Integer capacity,
        TableStatus status

){}
