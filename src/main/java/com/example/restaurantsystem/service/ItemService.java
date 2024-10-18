package com.example.restaurantsystem.service;

import com.example.restaurantsystem.dto.response.ItemResponse;
import com.example.restaurantsystem.dto.request.ItemRequest;

import java.util.List;

public interface ItemService {

    void addItem(ItemRequest item);
    void deleteById(Integer id);
    ItemResponse getById(Integer id);
    List<ItemResponse> getAll(int page, int count);

}
