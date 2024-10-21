package com.example.restaurantsystem.service;

import com.example.restaurantsystem.dto.response.ReservationResponse;
import com.example.restaurantsystem.dto.request.ReservationRequest;

import java.util.List;

public interface ReservationService {

    ReservationResponse addReservation(ReservationRequest reservation);
    void deleteById(Long id);
    ReservationResponse getById(Long id);
    List<ReservationResponse> getAll(int page, int count);
    ReservationResponse updateById(Long id, ReservationRequest reservation);
}
