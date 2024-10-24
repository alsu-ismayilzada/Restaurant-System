package com.example.restaurantsystem.groovy.service.impl

import com.example.restaurantsystem.dto.request.ReservationRequest
import com.example.restaurantsystem.dto.response.ItemResponse
import com.example.restaurantsystem.dto.response.ReservationResponse
import com.example.restaurantsystem.entity.Reservation
import com.example.restaurantsystem.entity.Table
import com.example.restaurantsystem.enums.TableStatus
import com.example.restaurantsystem.mapper.ReservationMapper
import com.example.restaurantsystem.repository.ReservationRepository
import com.example.restaurantsystem.repository.TableRepository
import com.example.restaurantsystem.service.ReservationService
import com.example.restaurantsystem.service.TableService
import com.example.restaurantsystem.service.impl.ReservationServiceImpl
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import spock.lang.Specification

class ReservationServiceImplTest extends Specification {
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private ReservationService reservationService
    private ReservationMapper reservationMapper
    private ReservationRepository reservationRepository
    private TableService tableService
    private TableRepository tableRepository

    def setup(){
        reservationRepository = Mock()
        reservationMapper = Mock()
        tableService = Mock()
        tableRepository = Mock()
        reservationService = new ReservationServiceImpl(reservationRepository, reservationMapper, tableService)
    }

    def "AddReservation success"() {
        given:
        def request = random.nextObject(ReservationRequest)
        def reservation = random.nextObject(Reservation)
        def response = random.nextObject(ReservationResponse)
        def table = random.nextObject(Table)
        table.status = TableStatus.EMPTY

        reservation.table = table

        when:
        reservationService.addReservation(request)

        then:
        1* tableService.findById(request.table) >> table
        1* tableService.bookTableById(table.id)
        1 * reservationMapper.toReservationEntity(request) >> reservation
        1* reservationRepository.save(reservation)
        1 * reservationMapper.toReservationDto(reservation) >> response
    }

//    def "AddReservation Table Not Found"() {
//        given:
//        def request = random.nextObject(ReservationRequest)
//
//        when:
//        reservationService.addReservation(request) >> { new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Not Found")}
//        1 * tableService.findById(request.table)
//
//        then:
//        1 * tableRepository.findById(request.table) >> Optional.empty()
//        0 * reservationRepository.save(request)
//
//        ResponseStatusException ex = thrown()
//        ex.reason == "Data Not Found"
//    }

//    def "AddReservation Table is already booked"() {
//        given:
//        def request = random.nextObject(ReservationRequest)
//        def table = random.nextObject(Table)
//        table.status = TableStatus.BOOKED
//
//        tableRepository.findById(request.table) >> Optional.of(table)
//
//        when:
//        reservationService.addReservation(request)
//
//        then:
//        1 * reservationMapper.toReservationEntity(request)
//        0 * reservationRepository.save(request)
//
//        ResponseStatusException ex = thrown()
//        ex.reason == "Table is already booked"
//    }


    def "DeleteById"() {
        given:
        def id = random.nextObject(Long)

        when:
        reservationService.deleteById(id)

        then:
        1* reservationRepository.deleteById(id)
    }

    def "GetById success"() {
        given:
        def id = random.nextObject(Long )
        def reservation = random.nextObject(Reservation)
        def response = random.nextObject(ReservationResponse)

        reservationRepository.findById(id) >> Optional.of(reservation)
        reservationMapper.toReservationDto(reservation) >> response

        when:
        def result = reservationService.getById(id)

        then:
        1* reservationRepository.findById(id) >> Optional.of(reservation)
        1* reservationMapper.toReservationDto(reservation) >> response
        result != null
        result == response
    }

    def "GetById Not Found"() {
        given:
        def id = random.nextObject(Long )
        def reservation = random.nextObject(Reservation)

        reservationRepository.findById(id) >> Optional.empty()

        when:
        def result = reservationService.getById(id)

        then:
        1* reservationRepository.findById(id) >> Optional.empty()
        0* reservationMapper.toReservationDto(reservation)

        ResponseStatusException ex = thrown()
        ex.reason == "Reservation not found"
    }

    def "GetAll"() {
        given:
        Pageable pageable = Mock(Pageable)
        List<Reservation> reservations = [random.nextObject(Reservation), random.nextObject(Reservation)]
        List<ReservationResponse> responses = reservations.collect{random.nextObject(ReservationResponse)}
        Page<Reservation> reservationPage = Mock(Page)

        when:
        reservationService.getAll(pageable)
        reservationPage.getContent() >> reservations
        reservationMapper.toReservationDto(_) >> { reservation -> responses[reservations.indexOf(reservation)] }

        then:
        1* reservationRepository.findAll(pageable) >> reservationPage
    }

    def "UpdateById success"() {
        given:
        def id = random.nextObject(Long)
        def request = random.nextObject(ReservationRequest)
        def reservation = random.nextObject(Reservation)
        def response = random.nextObject(ReservationResponse)
        def table = random.nextObject(Table)
        table.status = TableStatus.EMPTY

        reservation.table = table

        reservationMapper.toReservationEntity(request) >> reservation
        reservationMapper.toReservationDto(reservation) >> response
        reservationRepository.findById(id) >> Optional.of(reservation)

        when:
        def result = reservationService.updateById(id, request)

        then:
        1* reservationRepository.findById(id) >> Optional.of(reservation)
        1* reservationMapper.updateReservation(reservation, request)
        1* tableService.findById(request.table) >> table
        1* tableService.bookTableById(table.id)
        1 * reservationMapper.toReservationDto(reservation) >> response
        result != null
        result == response
    }

    def "UpdateById Not Found"() {
        given:
        def id = random.nextObject(Long)
        def request = random.nextObject(ReservationRequest)
        def reservation = random.nextObject(Reservation)
        def table = random.nextObject(Table)
        table.status = TableStatus.EMPTY

        reservation.table = table

        reservationRepository.findById(id) >> Optional.empty()

        when:
        reservationService.updateById(id, request)

        then:
        1* reservationRepository.findById(id) >> Optional.empty()
        0* reservationMapper.updateReservation(reservation, request)
        0* tableService.findById(request.table)
        0* tableService.bookTableById(table.id)
        0* reservationMapper.toReservationDto(reservation)

        ResponseStatusException ex = thrown()
        ex.reason == "Reservation not found"
    }

//    def "UpdateById Table Not Found"() {
//        given:
//        def id = random.nextObject(Long)
//        def request = random.nextObject(ReservationRequest)
//        def reservation = random.nextObject(Reservation)
//        def response = random.nextObject(ReservationResponse)
//        def table = random.nextObject(Table)
//        table.status = TableStatus.EMPTY
//
//        reservation.table = table
//
//        reservationRepository.findById(id) >> Optional.of(reservation)
//        tableService.findById(request.table) >> {new ResponseStatusException(HttpStatus.NOT_FOUND,"Data Not Found")}
//
//        when:
//        reservationService.updateById(id, request)
//
//        then:
//        1* reservationRepository.findById(id)
//        1* reservationMapper.updateReservation(reservation, request)
//        0* tableService.bookTableById(table.id)
//        0* reservationMapper.toReservationDto(reservation) >> response
//
//        ResponseStatusException ex = thrown()
//        ex.reason == "Data Not Found"
//    }

    def "FindById success"() {
        given:
        def id = random.nextObject(Long)
        def reservation = random.nextObject(Reservation)

        when:
        def result = reservationService.findById(id)

        then:
        1* reservationRepository.findById(id) >> Optional.of(reservation)
        result != null
        result == reservation
    }

    def "FindById Not Found"() {
        given:
        def id = random.nextObject(Long)

        when:
        reservationService.findById(id)

        then:
        1* reservationRepository.findById(id) >> Optional.empty()

        ResponseStatusException ex = thrown()
        ex.reason == "Reservation not found"

    }
}
