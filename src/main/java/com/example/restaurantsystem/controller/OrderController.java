package com.example.restaurantsystem.controller;

import com.example.restaurantsystem.dto.response.OrderResponse;
import com.example.restaurantsystem.dto.request.OrderRequest;
import com.example.restaurantsystem.service.impl.OrderServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;

    @PostMapping()
    public void addOrder(@RequestBody OrderRequest order){
        orderService.addOrder(order);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id){
        orderService.deleteById(id);
    }

    @GetMapping("{id}")
    public OrderResponse getById(@PathVariable Long id){
        return orderService.getById(id);
    }

    @GetMapping()
    public List<OrderResponse> getAll(@RequestParam(value = "page") int page, @RequestParam(value = "count") int count){
        return orderService.getAll(page,count);
    }

    @PutMapping("/{orderId}")
    public OrderResponse updateOrder(@PathVariable Long orderId, @RequestBody OrderRequest order){
        return orderService.updateById(orderId, order);
    }

    @GetMapping("/{id}/process")
    @Operation(summary = "Sifarişin hazırlanması")
    public ResponseEntity<OrderResponse> processOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.processOrder(id));
    }

    @GetMapping("/{id}/ship")
    @Operation(summary = "Sifarişin göndərilməsi")
    public ResponseEntity<OrderResponse> shipOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.shipOrder(id));
    }

    @GetMapping("/{id}/deliver")
    @Operation(summary = "Sifarişin çatdırılması")
    public ResponseEntity<OrderResponse> deliverOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.deliverOrder(id));
    }

    @GetMapping("/{id}/cancel")
    @Operation(summary = "Sifarişin ləğv edilməsi")
    public ResponseEntity<OrderResponse> cancelOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.cancelOrder(id));
    }

}
