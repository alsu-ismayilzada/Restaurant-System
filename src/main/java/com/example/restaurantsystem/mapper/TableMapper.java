package com.example.restaurantsystem.mapper;

import com.example.restaurantsystem.dto.response.TableResponse;
import com.example.restaurantsystem.dto.request.TableRequest;
import com.example.restaurantsystem.entity.Table;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TableMapper {

    TableResponse toTableDto(Table table);
    Table toTableEntity(TableRequest table);
    void updateTable(@MappingTarget Table table, TableRequest tableDto);
}
