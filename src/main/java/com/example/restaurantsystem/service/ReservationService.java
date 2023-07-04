package com.example.restaurantsystem.service;

import com.example.restaurantsystem.dto.ReservationDto;

import java.util.List;

public interface ReservationService {

    void addReservation(ReservationDto reservation);
    void deleteById(Integer id);
    ReservationDto getById(Integer id);
    List<ReservationDto> getAll();
}
