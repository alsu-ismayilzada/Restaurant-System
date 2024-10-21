package com.example.restaurantsystem.repository;

import com.example.restaurantsystem.entity.ItemInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemInfoRepository extends JpaRepository<ItemInfo, Long> {
}
