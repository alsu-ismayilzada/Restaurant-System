package com.example.restaurantsystem.service.impl;

import com.example.restaurantsystem.dto.response.ReservationResponse;
import com.example.restaurantsystem.dto.request.ReservationRequest;
import com.example.restaurantsystem.entity.Reservation;
import com.example.restaurantsystem.mapper.ReservationMapper;
import com.example.restaurantsystem.repository.ReservationRepository;
import com.example.restaurantsystem.service.ReservationService;
import com.example.restaurantsystem.service.TableService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final TableService tableService;


    @Override
    public ReservationResponse addReservation(ReservationRequest request) {
        var reservation = reservationMapper.toReservationEntity(request);
        var table = tableService.findById(request.getTable());
        tableService.bookTableById(table.getId());
        reservation.setTable(table);
        reservationRepository.save(reservation);
        return reservationMapper.toReservationDto(reservation);
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public ReservationResponse getById(Long id) {
        return reservationMapper.toReservationDto(findById(id));
    }

    @Override
    public List<ReservationResponse> getAll(Pageable pageable) {
        Page<Reservation> all = reservationRepository.findAll(pageable);

        return all.getContent()
                .stream().map(reservationMapper::toReservationDto)
                .toList();
    }

    @Override
    public ReservationResponse updateById(Long id, ReservationRequest request) {
        var reservation = findById(id);
        reservationMapper.updateReservation(reservation, request);
        if(request.getTable() != null) {
            var table = tableService.findById(request.getTable());
            tableService.bookTableById(table.getId());
            reservation.setTable(table);
        }
        return reservationMapper.toReservationDto(reservation);
    }

    @Override
    public Reservation findById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Reservation not found"));
    }
}
