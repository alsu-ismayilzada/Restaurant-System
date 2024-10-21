package com.example.restaurantsystem.dto.response;

import com.example.restaurantsystem.entity.Item;
import lombok.Data;

@Data
public class ItemInfoResponse {

    Item item;

    Integer count;
}