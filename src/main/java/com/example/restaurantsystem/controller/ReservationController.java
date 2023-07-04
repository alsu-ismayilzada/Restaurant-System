package com.example.restaurantsystem.controller;

import com.example.restaurantsystem.dto.ReservationDto;
import com.example.restaurantsystem.management.ReservationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reserv")
public class ReservationController {
    private final ReservationManager reservationManager;

    public ReservationController(@RequestBody ReservationManager reservationManager) {
        this.reservationManager = reservationManager;
    }
    @PostMapping("")
    public void addReservation(ReservationDto reservation){
        reservationManager.addReservation(reservation);
    }

    public void deleteById(@PathVariable Integer id){
        reservationManager.deleteById(id);
    }


}
