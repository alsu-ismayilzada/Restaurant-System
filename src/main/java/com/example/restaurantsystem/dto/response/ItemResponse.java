package com.example.restaurantsystem.dto.response;

import com.example.restaurantsystem.enums.ItemType;
import lombok.Data;

@Data
public class ItemResponse {

        Long id;
        String name;
        String photo;
        Double price;
        ItemType itemType;
}
