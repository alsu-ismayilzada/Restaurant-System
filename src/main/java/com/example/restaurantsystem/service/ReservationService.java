package com.example.restaurantsystem.service;

import com.example.restaurantsystem.dto.response.ReservationResponse;
import com.example.restaurantsystem.dto.request.ReservationRequest;
import com.example.restaurantsystem.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReservationService {

    ReservationResponse addReservation(ReservationRequest reservation);
    void deleteById(Long id);
    ReservationResponse getById(Long id);
    Page<ReservationResponse> getAll(Pageable pageable);
    ReservationResponse updateById(Long id, ReservationRequest reservation);
    Reservation findById(Long id);
}
