package com.example.restaurantsystem.mapper;

import com.example.restaurantsystem.dto.response.ReservationResponse;
import com.example.restaurantsystem.dto.request.ReservationRequest;
import com.example.restaurantsystem.entity.Reservation;
import com.example.restaurantsystem.service.TableService;
import com.example.restaurantsystem.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {TableService.class, UserService.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReservationMapper {

    ReservationResponse toReservationDto (Reservation reservation);

    Reservation toReservationEntity(ReservationRequest reservation);

    @Mapping(target = "table", ignore = true )
    void updateReservation (@MappingTarget Reservation reservation, ReservationRequest request);

}
