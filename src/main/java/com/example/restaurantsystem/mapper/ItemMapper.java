package com.example.restaurantsystem.mapper;

import com.example.restaurantsystem.dto.response.ItemResponse;
import com.example.restaurantsystem.dto.request.ItemRequest;
import com.example.restaurantsystem.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemResponse toItemDto(Item item);
    Item toItemEntity(ItemRequest item);
    void updateItem(@MappingTarget Item item, ItemRequest request);
}
