package com.example.restaurantsystem.service;

import com.example.restaurantsystem.entity.Table;

import java.util.List;

public interface TableService {

    void addTable(Table table);
    void deleteById(Integer id);
    Table getById(Integer id);
    List<Table> getAll();
}
