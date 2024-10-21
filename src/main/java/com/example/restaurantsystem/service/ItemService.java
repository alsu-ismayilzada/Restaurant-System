package com.example.restaurantsystem.service;

import com.example.restaurantsystem.dto.response.ItemResponse;
import com.example.restaurantsystem.dto.request.ItemRequest;
import com.example.restaurantsystem.entity.Item;

import java.util.List;

public interface ItemService {

    void addItem(ItemRequest item);
    void deleteById(Long id);
    ItemResponse getById(Long id);
    List<ItemResponse> getAll(int page, int count);
    ItemResponse updateById(Long id, ItemRequest item);
    Item findById(Long id);

}
