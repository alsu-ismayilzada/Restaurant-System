package com.example.restaurantsystem.dto.response;

import com.example.restaurantsystem.enums.ItemType;
import lombok.Data;

@Data
public class ItemResponse {
        String name;
        String photo;
        Double price;
        ItemType itemType;
}
