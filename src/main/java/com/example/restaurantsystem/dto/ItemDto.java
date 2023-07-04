package com.example.restaurantsystem.dto;

import com.example.restaurantsystem.entity.repository.ItemType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record ItemDto (
        String name,
        String photo,
        Double price,
        ItemType itemType
){
}
