package com.example.restaurantsystem.controller;

import com.example.restaurantsystem.dto.response.TableResponse;
import com.example.restaurantsystem.dto.request.TableRequest;
import com.example.restaurantsystem.service.impl.TableServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/table")
@RequiredArgsConstructor
public class TableController {

    private final TableServiceImpl tableService;

    @PostMapping()
    public void addTable(@RequestBody TableRequest table){
        tableService.addTable(table);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id){
        tableService.deleteById(id);
    }

    @GetMapping("{id}")
    public TableResponse getById(@PathVariable Integer id){
        return tableService.getById(id);
    }

    @GetMapping()
    public List<TableResponse> getAll(@RequestParam(value = "page") int page, @RequestParam(value = "count") int count){
        return tableService.getAll(page,count);
    }

    @PutMapping("/update/{tableId}")
    public TableResponse update(@PathVariable Integer tableId, @RequestBody TableRequest table){
        return tableService.updateById(tableId, table);
    }

}
