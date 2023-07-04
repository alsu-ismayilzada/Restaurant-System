package com.example.restaurantsystem.management;

import com.example.restaurantsystem.dto.ReservationDto;
import com.example.restaurantsystem.entity.Reservation;
import com.example.restaurantsystem.entity.Table;
import com.example.restaurantsystem.mapper.ReservationMapper;
import com.example.restaurantsystem.repository.ReservationRepository;
import com.example.restaurantsystem.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public List<ReservationDto> getAll(int page,int count) {
        Page<Reservation> all = reservationRepository.findAll(PageRequest.of(page,count));

        return all.getContent()
                .stream().map(reservationMapper::toReservationDto)
                .toList();
    }
}
