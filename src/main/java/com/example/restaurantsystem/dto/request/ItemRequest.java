package com.example.restaurantsystem.dto.request;

import com.example.restaurantsystem.repository.ItemType;
import lombok.Data;

@Data
public class ItemRequest {
    String name;
    String photo;
    Double price;
    ItemType itemType;

}
