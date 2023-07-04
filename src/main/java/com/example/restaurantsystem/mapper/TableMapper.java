package com.example.restaurantsystem.mapper;

import com.example.restaurantsystem.dto.TableDto;
import com.example.restaurantsystem.entity.Table;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TableMapper {

    TableDto toTableDto(Table table);
    Table toTableEntity(TableDto table);
}
