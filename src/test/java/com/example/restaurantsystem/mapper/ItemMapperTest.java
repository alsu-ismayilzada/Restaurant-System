package com.example.restaurantsystem.mapper;

import com.example.restaurantsystem.dto.ItemDto;
import com.example.restaurantsystem.entity.Item;
import com.example.restaurantsystem.entity.repository.ItemType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemMapperTest {

    private final ItemMapper itemMapper = new ItemMapperImpl();
    @Test
    void toItemDto() {
        //given
        Item item = new Item(null,"Pizza","sd",10.0, ItemType.FASTFOOD);
        ItemDto expected = new ItemDto("Pizza","sd",10.0, ItemType.FASTFOOD);
        //when
        ItemDto actual = itemMapper.toItemDto(item);
        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void toItemEntity() {
        //given
        ItemDto itemDto = new ItemDto("Pizza","sd",10.0, ItemType.FASTFOOD);
        Item expected = new Item(null,"Pizza","sd",10.0, ItemType.FASTFOOD);

        //when
        Item actual = itemMapper.toItemEntity(itemDto);
        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}