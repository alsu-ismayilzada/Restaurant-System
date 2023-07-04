package com.example.restaurantsystem.service;

import com.example.restaurantsystem.entity.Reservation;

import java.util.List;

public interface ReservationService {

    void addReservation(Reservation reservation);
    void rejectById(Integer id);
    Reservation getById(Integer id);
    List<Reservation> getAll();
}
