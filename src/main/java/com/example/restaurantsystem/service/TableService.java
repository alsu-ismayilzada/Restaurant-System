package com.example.restaurantsystem.service;

import com.example.restaurantsystem.dto.TableDto;
import com.example.restaurantsystem.entity.Table;

import java.util.List;

public interface TableService {

    void addTable(TableDto table);
    void deleteById(Integer id);
    TableDto getById(Integer id);
    List<TableDto> getAll(int page , int count);
}
