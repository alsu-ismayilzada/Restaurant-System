package com.example.restaurantsystem.controller;

import com.example.restaurantsystem.dto.response.TableResponse;
import com.example.restaurantsystem.dto.request.TableRequest;
import com.example.restaurantsystem.service.impl.TableServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/table")
@RequiredArgsConstructor
public class TableController {

    private final TableServiceImpl tableService;

    @PostMapping()
    public TableResponse addTable(@RequestBody TableRequest table){
        return tableService.saveTable(table);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id){
        tableService.deleteById(id);
    }

    @GetMapping("{id}")
    public TableResponse getById(@PathVariable Long id){
        return tableService.findTableResponseById(id);
    }

    @GetMapping()
    public List<TableResponse> getAll(@RequestParam(value = "page") int page, @RequestParam(value = "count") int count){
        return tableService.findAll(page,count);
    }

    @PutMapping("/update/{tableId}")
    public TableResponse update(@PathVariable Long tableId, @RequestBody TableRequest table){
        return tableService.updateById(tableId, table);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully booked"),
            @ApiResponse(responseCode = "400", description = "No booked")
    })
    @PostMapping("/book/{tableId}")
    public TableResponse bookTableById(@PathVariable Long tableId){
        return tableService.bookTableById(tableId);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully un-booked"),
            @ApiResponse(responseCode = "400", description = "No un-booked")
    })
    @PostMapping("/un-book/{tableId}")
    public TableResponse unBookTableById(@PathVariable Long tableId){
        return tableService.unBookTableById(tableId);
    }

}
