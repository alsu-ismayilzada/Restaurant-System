package com.example.restaurantsystem.mappertest;

import com.example.restaurantsystem.dto.ItemDto;
import com.example.restaurantsystem.entity.Item;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDto toItemDto(Item item);
    Item toItemEntity(ItemDto item);
}
