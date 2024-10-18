package com.example.restaurantsystem.dto;

import com.example.repository.TableStatus;

public record TableDto(

        Integer capacity,
        TableStatus status

){}
