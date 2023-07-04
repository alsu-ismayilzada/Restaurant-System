package com.example.restaurantsystem.management;

import com.example.restaurantsystem.dto.ItemDto;
import com.example.restaurantsystem.mapper.ItemMapper;
import com.example.restaurantsystem.repository.ItemRepository;
import com.example.restaurantsystem.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ItemManagement implements ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public void addItem(ItemDto item) {
        itemRepository.save(itemMapper.toItemEntity(item));
    }

    @Override
    public void deleteById(Integer id) {
        itemRepository.deleteById(id);
    }

    @Override
    public ItemDto getById(Integer id) {
        return itemRepository.findById(id)
                .stream().map(itemMapper::toItemDto)
                .findFirst().get();
    }

    @Override
    public List<ItemDto> getAll() {
        return itemRepository.findAll()
                .stream().map(itemMapper::toItemDto)
                .toList();
    }
}
