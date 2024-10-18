package com.example.restaurantsystem.mapper;

import com.example.restaurantsystem.dto.response.ItemResponse;
import com.example.restaurantsystem.dto.request.ItemRequest;
import com.example.restaurantsystem.entity.Item;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemResponse toItemDto(Item item);
    Item toItemEntity(ItemRequest item);
}
