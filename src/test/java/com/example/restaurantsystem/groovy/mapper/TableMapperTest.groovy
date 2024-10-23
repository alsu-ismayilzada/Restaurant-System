package com.example.restaurantsystem.groovy.mapper

import com.example.restaurantsystem.dto.request.TableRequest
import com.example.restaurantsystem.entity.Table
import com.example.restaurantsystem.mapper.TableMapper
import com.example.restaurantsystem.mapper.TableMapperImpl
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class TableMapperTest extends Specification {

    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private TableMapper tableMapper

    def setup(){
        tableMapper = new TableMapperImpl()
    }

    def "ToTableDto"() {
        given:
        def table = random.nextObject(Table)

        when:
        def response = tableMapper.toTableDto(table)

        then:
        table.id == response.id
        table.status == response.status
        table.capacity == response.capacity

    }

    def "ToTableEntity"() {
        given:
        def request = random.nextObject(TableRequest)

        when:
        def table = tableMapper.toTableEntity(request)

        then:
        request.capacity == table.capacity
    }

    def "UpdateTable"() {
        given:
        def table = random.nextObject(Table)
        def request = random.nextObject(TableRequest)

        when:
        tableMapper.updateTable(table, request)

        then:
        table.capacity == request.capacity
    }
}
