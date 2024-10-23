package com.example.restaurantsystem.groovy.service.impl

import com.example.restaurantsystem.dto.request.TableRequest
import com.example.restaurantsystem.dto.response.TableResponse
import com.example.restaurantsystem.entity.Table
import com.example.restaurantsystem.enums.TableStatus
import com.example.restaurantsystem.mapper.TableMapper
import com.example.restaurantsystem.repository.TableRepository
import com.example.restaurantsystem.service.TableService
import com.example.restaurantsystem.service.impl.TableServiceImpl
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.springframework.web.server.ResponseStatusException
import spock.lang.Specification

class TableServiceImplTest extends Specification {

    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private TableRepository tableRepository
    private TableMapper tableMapper
    private TableService tableService

    def setup(){
        tableRepository = Mock()
        tableMapper =  Mock()
        tableService = new TableServiceImpl(tableRepository,tableMapper)
    }

    def "SaveTable"() {
        given:
        def table = random.nextObject(Table)
        def request = random.nextObject(TableRequest)
        def response = random.nextObject(TableResponse)

        when:
        tableMapper.toTableEntity(request) >> table
        tableMapper.toTableDto(table) >> response
        def result = tableService.saveTable(request)

        then:
        1* tableRepository.save(table)
    }

    def "DeleteById"() {
        given:
        def id = random.nextObject(Long)

        when:
        tableService.deleteById(id)

        then:
        1* tableRepository.deleteById(id)
    }

    def "FindTableResponseById success"() {
        given:
        def id = random.nextObject(Long)
        def response = random.nextObject(TableResponse)
        def table = random.nextObject(Table)

        when:
        tableService.findTableResponseById(id)

        then:
        tableMapper.toTableDto(table) >> response
        1*tableRepository.findById(id) >> Optional.of(table)
    }

    def "FindTableResponseById Not Found"() {
        given:
        def id = random.nextObject(Long)

        when:
        tableService.findTableResponseById(id)

        then:
        1*tableRepository.findById(id) >> Optional.empty()

        ResponseStatusException ex = thrown()
        ex.reason == "Data Not Found"
    }

    def "FindAll"() {
    }

    def "UpdateById success"() {
        given:
        def id = random.nextObject(Long)
        def request = random.nextObject(TableRequest)
        def table = random.nextObject(Table)
        def response = random.nextObject(TableResponse)

        when:
        tableService.updateById(id, request)
        tableMapper.updateTable(table, request) >> {}
        tableMapper.toTableDto(table) >> response

        then:
        1* tableRepository.findById(id) >> Optional.of(table)

    }

    def "UpdateById Not Found"() {
        given:
        def id = random.nextObject(Long)
        def request = random.nextObject(TableRequest)

        when:
        tableService.updateById(id, request)

        then:
        1* tableRepository.findById(id) >> Optional.empty()

        ResponseStatusException ex = thrown()
        ex.reason == "Data Not Found"

    }

    def "BookTableById success"() {
        given:
        def id = random.nextObject(Long)
        def table = random.nextObject (Table)
        table.setStatus(TableStatus.EMPTY)
        tableRepository.findById(id) >> Optional.of(table)

        when:
        tableService.bookTableById(id)
        table.setStatus(TableStatus.BOOKED)

        then:
        1* tableRepository.save(table)
        1* tableMapper.toTableDto(table)
    }

    def "BookTableById Already Booked"() {
        given:
        def id = random.nextObject(Long)
        def table = random.nextObject (Table)
        table.setStatus(TableStatus.BOOKED)
        tableRepository.findById(id) >> Optional.of(table)

        when:
        tableService.bookTableById(id)

        then:
        0* tableRepository.save(table)
        0* tableMapper.toTableDto(table)

        ResponseStatusException ex = thrown()
        ex.reason == "Table is already booked"
    }

    def "BookTableById Not Found"() {
        given:
        def id = random.nextObject(Long)
        def table = random.nextObject (Table)
        tableRepository.findById(id) >> Optional.empty()

        when:
        tableService.bookTableById(id)

        then:
        0* tableRepository.save(table)
        0* tableMapper.toTableDto(table)

        ResponseStatusException ex = thrown()
        ex.reason == "Data Not Found"
    }

    def "UnBookTableById Success"() {
        given:
        def id = random.nextObject(Long)
        def table = random.nextObject (Table)
        table.setStatus(TableStatus.BOOKED)
        tableRepository.findById(id) >> Optional.of(table)

        when:
        tableService.unBookTableById(id)
        table.setStatus(TableStatus.EMPTY)

        then:
        1* tableRepository.save(table)
        1* tableMapper.toTableDto(table)
    }

    def "UnBookTableById Success Already Empty"() {
        given:
        def id = random.nextObject(Long)
        def table = random.nextObject (Table)
        table.setStatus(TableStatus.EMPTY)
        tableRepository.findById(id) >> Optional.of(table)

        when:
        tableService.unBookTableById(id)

        then:
        0* tableRepository.save(table)
        0* tableMapper.toTableDto(table)

        ResponseStatusException ex = thrown()
        ex.reason == "Table is already empty"
    }

    def "UnBookTableById Success Not Found"() {
        given:
        def id = random.nextObject(Long)
        def table = random.nextObject (Table)
        tableRepository.findById(id) >> Optional.empty()

        when:
        tableService.unBookTableById(id)

        then:
        0* tableRepository.save(table)
        0* tableMapper.toTableDto(table)

        ResponseStatusException ex = thrown()
        ex.reason == "Data Not Found"
    }

    def "FindById success"() {
        given:
        def id = random.nextObject(Long)
        def table = random.nextObject(Table)

        when:
        tableService.findById(id)

        then:
        1* tableRepository.findById(id) >> Optional.of(table)
    }

    def "FindById Not Found"() {
        given:
        def id = random.nextObject(Long)

        when:
        tableService.findById(id)

        then:
        1* tableRepository.findById(id) >> Optional.empty()

        ResponseStatusException ex = thrown()
        ex.reason == "Data Not Found"
    }
}
