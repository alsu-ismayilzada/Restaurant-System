package com.example.restaurantsystem.mapper;

import com.example.restaurantsystem.dto.response.ReservationResponse;
import com.example.restaurantsystem.dto.request.ReservationRequest;
import com.example.restaurantsystem.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationResponse toReservationDto (Reservation reservation);
    Reservation toReservationEntity(ReservationRequest reservation);
    void updateReservation (@MappingTarget Reservation reservation, ReservationRequest request);

}
