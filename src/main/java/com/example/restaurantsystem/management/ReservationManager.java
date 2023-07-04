package com.example.restaurantsystem.management;

import com.example.restaurantsystem.dto.ReservationDto;
import com.example.restaurantsystem.mapper.ReservationMapper;
import com.example.restaurantsystem.repository.ReservationRepository;
import com.example.restaurantsystem.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ReservationManager implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @Override
    public void addReservation(ReservationDto reservation) {
        reservationRepository.save(reservationMapper.toReservationEntity(reservation));
    }

    @Override
    public void deleteById(Integer id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public ReservationDto getById(Integer id) {
        return reservationRepository.findById(id)
                .stream().map(reservationMapper::toReservationDto)
                .findFirst().get();
    }

    @Override
    public List<ReservationDto> getAll() {
        return reservationRepository.findAll()
                .stream().map(reservationMapper::toReservationDto)
                .toList();
    }
}
