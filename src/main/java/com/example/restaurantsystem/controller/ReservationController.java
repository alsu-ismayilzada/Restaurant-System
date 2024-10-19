package com.example.restaurantsystem.controller;
import com.example.restaurantsystem.dto.response.ReservationResponse;
import com.example.restaurantsystem.dto.request.ReservationRequest;
import com.example.restaurantsystem.service.impl.ReservationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reserv")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationServiceImpl reservationService;

    @PostMapping("")
    public ReservationResponse addReservation(ReservationRequest reservation){
        return reservationService.addReservation(reservation);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id){
        reservationService.deleteById(id);
    }

    @GetMapping("{id}")
    public ReservationResponse getById(@PathVariable Integer id){
        return reservationService.getById(id);
    }

    @GetMapping()
    public List<ReservationResponse> getAll(@RequestParam(value = "page") int page, @RequestParam(value = "count") int count){
        return reservationService.getAll(page,count);
    }

    @PutMapping("/{reservationId}")
    public ReservationResponse updateReservation(@RequestBody ReservationRequest reservation, @PathVariable Integer reservationId){
        return reservationService.updateById(reservationId, reservation);
    }

}

