package com.example.restaurantsystem.service.impl;
import com.example.restaurantsystem.dto.response.ItemResponse;
import com.example.restaurantsystem.dto.request.ItemRequest;
import com.example.restaurantsystem.entity.Item;
import com.example.restaurantsystem.mapper.ItemMapper;
import com.example.restaurantsystem.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements com.example.restaurantsystem.service.ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public void addItem(ItemRequest item) {
        itemRepository.save(itemMapper.toItemEntity(item));
    }

    @Override
    public void deleteById(Integer id) {
        itemRepository.deleteById(id);
    }
    @Override
    public ItemResponse getById(Integer id) {
        return itemMapper.toItemDto(findById(id));
    }
    @Override
    public List<ItemResponse> getAll(int page, int count) {
        Page<Item> all = itemRepository.findAll(PageRequest.of(page,count));
        return all.getContent()
                .stream().map(itemMapper::toItemDto)
                .toList();
    }

    @Override
    public ItemResponse updateById(Integer id, ItemRequest request) {
        var item = findById(id);
        itemMapper.updateItem(item, request);
        return itemMapper.toItemDto(item);
    }

    public Item findById(Integer id){
        return itemRepository.findById(id).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Item Not Found"));
    }
}
