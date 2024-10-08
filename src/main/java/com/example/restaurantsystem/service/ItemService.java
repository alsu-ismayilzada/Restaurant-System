package com.example.restaurantsystem.service;

import com.example.restaurantsystem.dto.ItemDto;
import com.example.restaurantsystem.entity.Item;

import java.util.List;

public interface ItemService {

    void addItem(ItemDto item);
    void deleteById(Integer id);
    ItemDto getById(Integer id);
    List<ItemDto> getAll(int page,int count);

}
