package com.example.restaurantsystem.service;

import com.example.restaurantsystem.entity.Item;

import java.util.List;

public interface ItemService {


    void addItem(Item item);
    void deleteById(Integer id);
    Item getById(Integer id);
    List<Item> getAll();

}
