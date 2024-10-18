package com.example.restaurantsystem.dto;

import com.example.repository.ItemType;
import lombok.Data;

@Data
public class ItemDto{
        String name;
        String photo;
        Double price;
        ItemType itemType;

}
