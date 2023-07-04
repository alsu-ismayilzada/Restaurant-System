package com.example.restaurantsystem.service;

import com.example.restaurantsystem.dto.ReservationDto;
import com.example.restaurantsystem.entity.Reservation;

import java.util.List;

public interface ReservationService {

    void addReservation(ReservationDto reservation);
    void rejectById(Integer id);
    ReservationDto getById(Integer id);
    List<ReservationDto> getAll();
}
