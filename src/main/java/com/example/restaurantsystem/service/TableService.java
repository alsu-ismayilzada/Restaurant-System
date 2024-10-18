package com.example.restaurantsystem.service;

import com.example.restaurantsystem.dto.response.TableResponse;
import com.example.restaurantsystem.dto.request.TableRequest;

import java.util.List;

public interface TableService {

    void addTable(TableRequest table);
    void deleteById(Integer id);
    TableResponse getById(Integer id);
    List<TableResponse> getAll(int page , int count);
}
