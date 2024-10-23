package com.example.restaurantsystem.junit.mappertest;

import com.example.restaurantsystem.dto.request.TableRequest;
import com.example.restaurantsystem.entity.Table;
import com.example.restaurantsystem.mapper.TableMapper;
import com.example.restaurantsystem.mapper.TableMapperImpl;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class TableMapperTest {

    private final EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom();
     private final TableMapper tableMapper = new TableMapperImpl();

    @Test
    void toTableDto() {
        //given
        var table = random.nextObject(Table.class);

        //when
        var response = tableMapper.toTableDto(table);

        //then
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getId()).isEqualTo(table.getId());
        Assertions.assertThat(response.getCapacity()).isEqualTo(table.getCapacity());
        Assertions.assertThat(response.getStatus()).isEqualTo(table.getStatus());
    }

    @Test
    void toTableEntity() {
        //given
        var request = random.nextObject(TableRequest.class);

        //when
        var table = tableMapper.toTableEntity(request);

        //then
        Assertions.assertThat(table).isNotNull();
        Assertions.assertThat(table.getCapacity()).isEqualTo(request.getCapacity());
    }

    @Test
    void updateTable() {
        //given
        var request = random.nextObject(TableRequest.class);
        var table = random.nextObject(Table.class);

        //when
        tableMapper.updateTable(table, request);

        //then
        Assertions.assertThat(table).isNotNull();
        Assertions.assertThat(table.getCapacity()).isEqualTo(request.getCapacity());
    }
}