package com.example.restaurantsystem.dto.response;

import com.example.restaurantsystem.enums.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse {

        Long id;
        String name;
        String photo;
        Double price;
        ItemType itemType;
}
