package com.example.restaurantsystem.controller;

import com.example.restaurantsystem.dto.ItemDto;
import com.example.restaurantsystem.service.impl.ItemManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    private final ItemManager itemManager;

    public ItemController(ItemManager itemManager) {
        this.itemManager = itemManager;
    }
    @PostMapping
    public void addItem(@RequestBody ItemDto item){
        itemManager.addItem(item);
    }
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id){
        itemManager.deleteById(id);
    }
    public ItemDto getById(@PathVariable Integer id){
        return itemManager.getById(id);
    }
    public List<ItemDto> getAll(@RequestParam(value = "page") int page, @RequestParam(value = "count") int count){
        return itemManager.getAll(page,count);
    }
}
