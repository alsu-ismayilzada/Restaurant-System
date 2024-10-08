package com.example.restaurantsystem.service.impl;
import com.example.restaurantsystem.dto.ItemDto;
import com.example.restaurantsystem.entity.Item;
import com.example.restaurantsystem.mapper.ItemMapper;
import com.example.restaurantsystem.repository.ItemRepository;
import com.example.restaurantsystem.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemManager implements ItemService {
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
    public List<ItemDto> getAll(int page,int count) {
        Page<Item> all = itemRepository.findAll(PageRequest.of(page,count));
        return all.getContent()
                .stream().map(itemMapper::toItemDto)
                .toList();
    }
}
