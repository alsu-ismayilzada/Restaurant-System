package com.example.restaurantsystem.service.impl;

import com.example.restaurantsystem.entity.ItemInfo;
import com.example.restaurantsystem.repository.ItemInfoRepository;
import com.example.restaurantsystem.service.ItemInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ItemInfoServiceImpl implements ItemInfoService {

    private final ItemInfoRepository itemInfoRepository;

    @Override
    public ItemInfo findItemInfoById(Long id) {
        return itemInfoRepository.findById(id).orElseThrow(()->{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"ItemInfo not found");
        });
    }

    @Override
    public ItemInfo saveItemInfo(ItemInfo itemInfo) {
        return itemInfoRepository.save(itemInfo);
    }
}
