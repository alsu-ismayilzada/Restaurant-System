package com.example.restaurantsystem.service;

import com.example.restaurantsystem.entity.ItemInfo;

public interface ItemInfoService {
    ItemInfo findItemInfoById(Long id);
    ItemInfo saveItemInfo(ItemInfo itemInfo);

}
