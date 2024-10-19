package com.example.restaurantsystem.service;

import com.example.restaurantsystem.dto.response.TableResponse;
import com.example.restaurantsystem.dto.request.TableRequest;
import com.example.restaurantsystem.entity.Table;

import java.util.List;

public interface TableService {

    TableResponse saveTable(TableRequest table);
    void deleteById(Long id);
    TableResponse findTableResponseById(Long id);
    List<TableResponse> findAll(int page , int count);
    TableResponse updateById(Long id, TableRequest table);
    TableResponse bookTableById(Long id);
    TableResponse unBookTableById(Long id);
    Table findById(Long id);
}
