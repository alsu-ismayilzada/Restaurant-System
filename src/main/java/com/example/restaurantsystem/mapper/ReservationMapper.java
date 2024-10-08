package com.example.restaurantsystem.mapper;

import com.example.restaurantsystem.dto.ReservationDto;
import com.example.restaurantsystem.entity.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationDto toReservationDto (Reservation reservation);
    Reservation toReservationEntity(ReservationDto reservation);

}
