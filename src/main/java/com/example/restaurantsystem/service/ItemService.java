package com.example.restaurantsystem.service;

import com.example.restaurantsystem.dto.response.ItemResponse;
import com.example.restaurantsystem.dto.request.ItemRequest;
import com.example.restaurantsystem.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemService {

    void addItem(ItemRequest item);
    void deleteById(Long id);
    ItemResponse getById(Long id);
    Page<ItemResponse> getAll(Pageable pageable);
    ItemResponse updateById(Long id, ItemRequest item);
    Item findById(Long id);
    List<Item> findAllById(List<Long> ids);

}
