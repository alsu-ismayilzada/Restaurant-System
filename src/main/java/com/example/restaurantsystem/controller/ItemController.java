package com.example.restaurantsystem.controller;

import com.example.restaurantsystem.dto.response.ItemResponse;
import com.example.restaurantsystem.dto.request.ItemRequest;
import com.example.restaurantsystem.service.impl.ItemServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    private final ItemServiceImpl itemManager;

    public ItemController(ItemServiceImpl itemManager) {
        this.itemManager = itemManager;
    }
    @PostMapping
    public void addItem(@RequestBody ItemRequest item){
        itemManager.addItem(item);
    }
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id){
        itemManager.deleteById(id);
    }
    public ItemResponse getById(@PathVariable Integer id){
        return itemManager.getById(id);
    }
    public List<ItemResponse> getAll(@RequestParam(value = "page") int page, @RequestParam(value = "count") int count){
        return itemManager.getAll(page,count);
    }
}
