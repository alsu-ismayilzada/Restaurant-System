package com.example.restaurantsystem.dto.request;

import com.example.restaurantsystem.entity.Item;
import lombok.Data;

@Data
public class ItemInfoRequest {

    Long item;

    Integer count;
}
