package com.example.restaurantsystem.controller;
import com.example.restaurantsystem.dto.response.ReservationResponse;
import com.example.restaurantsystem.dto.request.ReservationRequest;
import com.example.restaurantsystem.service.ReservationService;
import com.example.restaurantsystem.service.impl.ReservationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping()
    public ReservationResponse addReservation(ReservationRequest reservation){
        return reservationService.addReservation(reservation);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id){
        reservationService.deleteById(id);
    }

    @GetMapping("{id}")
    public ReservationResponse getById(@PathVariable Long id){
        return reservationService.getById(id);
    }

    @GetMapping()
    public Page<ReservationResponse> getAll(Pageable pageable){
        return reservationService.getAll(pageable);
    }

    @PutMapping("/{reservationId}")
    public ReservationResponse updateReservation(@RequestBody ReservationRequest reservation, @PathVariable Long reservationId){
        return reservationService.updateById(reservationId, reservation);
    }

}

