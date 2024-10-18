package com.example.restaurantsystem.controller;
import com.example.restaurantsystem.dto.response.ReservationResponse;
import com.example.restaurantsystem.dto.request.ReservationRequest;
import com.example.restaurantsystem.service.impl.ReservationServiceImpl;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reserv")
public class ReservationController {
    private final ReservationServiceImpl reservationManager;

    public ReservationController(@RequestBody ReservationServiceImpl reservationManager) {
        this.reservationManager = reservationManager;
    }
    @PostMapping("")
    public void addReservation(ReservationRequest reservation){
        reservationManager.addReservation(reservation);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id){
        reservationManager.deleteById(id);
    }
    @GetMapping("{id}")
    public ReservationResponse getById(@PathVariable Integer id){
        return reservationManager.getById(id);
    }
    @GetMapping()
    public List<ReservationResponse> getAll(@RequestParam(value = "page") int page, @RequestParam(value = "count") int count){
        return reservationManager.getAll(page,count);
    }

}

