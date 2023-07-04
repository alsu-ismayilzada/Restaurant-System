package com.example.restaurantsystem.management;

import com.example.restaurantsystem.dto.TableDto;
import com.example.restaurantsystem.mapper.TableMapper;
import com.example.restaurantsystem.repository.TableRepository;
import com.example.restaurantsystem.service.TableService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class TableManager implements TableService {

    private final TableRepository tableRepository;
    private final TableMapper tableMapper;

    @Override
    public void addTable(TableDto table) {
        tableRepository.save(tableMapper.toTableEntity(table));
    }

    @Override
    public void deleteById(Integer id) {
        tableRepository.deleteById(id);
    }

    @Override
    public TableDto getById(Integer id) {
        return tableRepository.findById(id)
                .stream().map(tableMapper::toTableDto)
                .findFirst().get();
    }

    @Override
    public List<TableDto> getAll() {
        return tableRepository.findAll()
                .stream().map(tableMapper::toTableDto)
                .toList();
    }
}
