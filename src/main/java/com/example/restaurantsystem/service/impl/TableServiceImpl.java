package com.example.restaurantsystem.service.impl;

import com.example.restaurantsystem.dto.response.TableResponse;
import com.example.restaurantsystem.dto.request.TableRequest;
import com.example.restaurantsystem.entity.Table;
import com.example.restaurantsystem.enums.TableStatus;
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
    public TableResponse saveTable(TableRequest table) {
       var tableEntity = tableMapper.toTableEntity(table);
       tableEntity.setStatus(TableStatus.EMPTY);
       tableRepository.save(tableEntity);
       return tableMapper.toTableDto(tableEntity);
    }

    @Override
    public void deleteById(Long id) {
        tableRepository.deleteById(id);
    }

    @Override
    public TableResponse findTableResponseById(Long id) {
        return tableMapper.toTableDto(findById(id));
    }

    @Override
    public List<TableResponse> findAll(int page, int count) {
        Page<Table> all = tableRepository.findAll(PageRequest.of(page,count));

        return all.getContent()
                .stream().map(tableMapper::toTableDto)
                .toList();
    }

    @Override
    public TableResponse updateById(Long id, TableRequest request) {
        var table = findById(id);
        tableMapper.updateTable(table, request);
        return tableMapper.toTableDto(table);
    }

    @Override
    public TableResponse bookTableById(Long id){
       var table = findById(id);
       if(table.getStatus() != TableStatus.EMPTY){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Table is already booked");
       }
       table.setStatus(TableStatus.BOOKED);
       tableRepository.save(table);
       return tableMapper.toTableDto(table);
    }

    @Override
    public TableResponse unBookTableById(Long id){
        var table = findById(id);
        if(table.getStatus() != TableStatus.BOOKED){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Table is already empty");
        }
        table.setStatus(TableStatus.EMPTY);
        tableRepository.save(table);
        return tableMapper.toTableDto(table);
    }

    @Override
    public Table findById(Long id) {
        return tableRepository.findById(id)
                .orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND,"Data Not Found"));
    }
}
