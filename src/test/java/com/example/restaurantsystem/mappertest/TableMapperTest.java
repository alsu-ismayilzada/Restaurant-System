package com.example.restaurantsystem.mappertest;

import com.example.restaurantsystem.dto.TableDto;
import com.example.restaurantsystem.entity.Table;
import com.example.restaurantsystem.entity.repository.TableStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class TableMapperTest {

     private final TableMapper tableMapper = new TableMapperImpl();
    @Test
    void toTableDto() {
        //given
        Table table = new Table(null,2, TableStatus.EMPTY);
        TableDto expected = new TableDto(2,TableStatus.EMPTY);
        //when
        TableDto actual = tableMapper.toTableDto(table);
        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void toTableEntity() {
        //given
        TableDto tableDto = new TableDto(2,TableStatus.EMPTY);
        Table expected = new Table(null,2, TableStatus.EMPTY);
        //when
        Table actual = tableMapper.toTableEntity(tableDto);
        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}