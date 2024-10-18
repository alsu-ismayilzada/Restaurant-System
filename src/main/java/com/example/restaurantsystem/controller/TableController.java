package com.example.restaurantsystem.controller;

import com.example.restaurantsystem.dto.response.TableResponse;
import com.example.restaurantsystem.dto.request.TableRequest;
import com.example.restaurantsystem.service.impl.TableServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/table")
public class TableController {
    private final TableServiceImpl tableManager;

    public TableController(TableServiceImpl tableManager) {
        this.tableManager = tableManager;
    }
    @PostMapping()
    public void addTable(@RequestBody TableRequest table){
        tableManager.addTable(table);
    }
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id){
        tableManager.deleteById(id);
    }
    @GetMapping("{id}")
    public TableResponse getById(@PathVariable Integer id){
        return tableManager.getById(id);
    }
    public List<TableResponse> getAll(@RequestParam(value = "page") int page, @RequestParam(value = "count") int count){
        return tableManager.getAll(page,count);
    }
}
