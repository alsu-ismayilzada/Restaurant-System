package com.example.restaurantsystem.service.impl;
import com.example.restaurantsystem.dto.response.ItemResponse;
import com.example.restaurantsystem.dto.request.ItemRequest;
import com.example.restaurantsystem.entity.Item;
import com.example.restaurantsystem.mapper.ItemMapper;
import com.example.restaurantsystem.repository.ItemRepository;
import com.example.restaurantsystem.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public void addItem(ItemRequest item) {
        itemRepository.save(itemMapper.toItemEntity(item));
    }

    @Override
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public ItemResponse getById(Long id) {
        return itemMapper.toItemDto(findById(id));
    }

    @Override
    public Page<ItemResponse> getAll(Pageable pageable) {
        return itemRepository.findAll(pageable)
                .map(itemMapper::toItemDto);
    }

    @Override
    public ItemResponse updateById(Long id, ItemRequest request) {
        var item = findById(id);
        itemMapper.updateItem(item, request);
        return itemMapper.toItemDto(item);
    }

    public Item findById(Long id){
        return itemRepository.findById(id).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Item Not Found"));
    }

    @Override
    public List<Item> findAllById(List<Long> ids) {
        return itemRepository.findAllById(ids);
    }
}
