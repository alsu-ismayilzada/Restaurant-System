package com.example.restaurantsystem.junit.mappertest;

import com.example.restaurantsystem.dto.request.ItemRequest;
import com.example.restaurantsystem.entity.Item;
import com.example.restaurantsystem.mapper.ItemMapper;
import com.example.restaurantsystem.mapper.ItemMapperImpl;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ItemMapperTest {

    private final EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom();
    private final ItemMapper itemMapper = new ItemMapperImpl();

    @Test
    void toItemDto() {
        //given
        var item = random.nextObject(Item.class);

        //when
        var response = itemMapper.toItemDto(item);

        //then
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getId()).isEqualTo(item.getId());
        Assertions.assertThat(response.getName()).isEqualTo(item.getName());
        Assertions.assertThat(response.getPhoto()).isEqualTo(item.getPhoto());
        Assertions.assertThat(response.getPrice()).isEqualTo(item.getPrice());
        Assertions.assertThat(response.getItemType()).isEqualTo(item.getItemType());
    }

    @Test
    void toItemEntity() {
        //given
        var request = random.nextObject(ItemRequest.class);

        //when
        var item = itemMapper.toItemEntity(request);

        //then
        Assertions.assertThat(item).isNotNull();
        Assertions.assertThat(item.getName()).isEqualTo(request.getName());
        Assertions.assertThat(item.getPhoto()).isEqualTo(request.getPhoto());
        Assertions.assertThat(item.getPrice()).isEqualTo(request.getPrice());
        Assertions.assertThat(item.getItemType()).isEqualTo(request.getItemType());
    }

    @Test
    void updateItem() {
        //given
        var item = random.nextObject(Item.class);
        var request = random.nextObject(ItemRequest.class);

        //when
        itemMapper.updateItem(item, request);

        //then
        Assertions.assertThat(item).isNotNull();
        Assertions.assertThat(item.getName()).isEqualTo(request.getName());
        Assertions.assertThat(item.getPhoto()).isEqualTo(request.getPhoto());
        Assertions.assertThat(item.getPrice()).isEqualTo(request.getPrice());
        Assertions.assertThat(item.getItemType()).isEqualTo(request.getItemType());
    }
}