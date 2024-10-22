package com.example.restaurantsystem.controller;

import com.example.restaurantsystem.dto.response.ItemResponse;
import com.example.restaurantsystem.dto.request.ItemRequest;
import com.example.restaurantsystem.service.impl.ItemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public void deleteById(@PathVariable Long id){
        itemService.deleteById(id);
    }

    @GetMapping("/{id}")
    public ItemResponse getById(@PathVariable Long id){
        return itemService.getById(id);
    }

    @GetMapping
    public Page<ItemResponse> getAll(Pageable pageable){
        return itemService.getAll(pageable);
    }

    @PutMapping("/{itemId}")
    public ItemResponse updateItem(@PathVariable Long itemId, @RequestBody ItemRequest item){
        return itemService.updateById(itemId,item);
    }
}
