package com.example.restaurantsystem.controller;

import com.example.restaurantsystem.dto.TableDto;
import com.example.restaurantsystem.entity.Table;
import com.example.restaurantsystem.management.TableManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/table")
public class TableController {
    private final TableManager tableManager;

    public TableController(TableManager tableManager) {
        this.tableManager = tableManager;
    }
    @PostMapping()
    public void addTable(@RequestBody TableDto table){
        tableManager.addTable(table);
    }
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id){
        tableManager.deleteById(id);
    }
    @GetMapping("{id}")
    public TableDto getById(@PathVariable Integer id){
        return tableManager.getById(id);
    }
    public List<TableDto> getAll(@RequestParam(value = "page") int page,@RequestParam(value = "count") int count){
        return tableManager.getAll(page,count);
    }
}
