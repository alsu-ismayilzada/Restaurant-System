package com.example.restaurantsystem.controller;

import com.example.restaurantsystem.dto.response.ItemResponse;
import com.example.restaurantsystem.dto.request.ItemRequest;
import com.example.restaurantsystem.service.impl.ItemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemServiceImpl itemService;

    @PostMapping
    public void addItem(@RequestBody ItemRequest item){
        itemService.addItem(item);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id){
        itemService.deleteById(id);
    }

    @GetMapping("/{id}")
    public ItemResponse getById(@PathVariable Integer id){
        return itemService.getById(id);
    }

    @GetMapping
    public List<ItemResponse> getAll(@RequestParam(value = "page") int page, @RequestParam(value = "count") int count){
        return itemService.getAll(page,count);
    }

    @PutMapping("/{itemId}")
    public ItemResponse updateItem(@PathVariable Integer itemId, @RequestBody ItemRequest item){
        return itemService.updateById(itemId,item);
    }
}
