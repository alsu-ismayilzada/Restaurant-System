package com.example.restaurantsystem.service.impl;

import com.example.restaurantsystem.dto.response.TableResponse;
import com.example.restaurantsystem.dto.request.TableRequest;
import com.example.restaurantsystem.entity.Table;
import com.example.restaurantsystem.mapper.TableMapper;
import com.example.restaurantsystem.repository.TableRepository;
import com.example.restaurantsystem.service.TableService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
@AllArgsConstructor
public class TableServiceImpl implements TableService {

    private final TableRepository tableRepository;
    private final TableMapper tableMapper;

    @Override
    public void addTable(TableRequest table) {
        tableRepository.save(tableMapper.toTableEntity(table));
    }

    @Override
    public void deleteById(Integer id) {
        tableRepository.deleteById(id);
    }

    @Override
    public TableResponse getById(Integer id) {
        return tableMapper.toTableDto(findById(id));
    }

    @Override
    public List<TableResponse> getAll(int page, int count) {
        Page<Table> all = tableRepository.findAll(PageRequest.of(page,count));

        return all.getContent()
                .stream().map(tableMapper::toTableDto)
                .toList();
    }

    @Override
    public TableResponse updateById(Integer id, TableRequest request) {
        var table = findById(id);
        tableMapper.updateTable(table, request);
        return tableMapper.toTableDto(table);
    }

    public Table findById(Integer id) {
        return tableRepository.findById(id)
                .orElseThrow(() ->{
                    return new ResponseStatusException(HttpStatus.NOT_FOUND,"Data Not Found");
                });
    }
}
