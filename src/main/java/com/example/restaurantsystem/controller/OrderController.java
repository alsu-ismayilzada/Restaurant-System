package com.example.restaurantsystem.controller;

import com.example.restaurantsystem.dto.response.OrderResponse;
import com.example.restaurantsystem.dto.request.OrderRequest;
import com.example.restaurantsystem.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
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
    public void deleteById(@PathVariable Integer id){
        orderService.deleteById(id);
    }

    @GetMapping("{id}")
    public OrderResponse getById(@PathVariable Integer id){
        return orderService.getById(id);
    }

    @GetMapping()
    public List<OrderResponse> getAll(@RequestParam(value = "page") int page, @RequestParam(value = "count") int count){
        return orderService.getAll(page,count);
    }

    @PutMapping("/{orderId}")
    public OrderResponse updateOrder(@PathVariable Integer orderId, @RequestBody OrderRequest order){
        return orderService.updateById(orderId, order);
    }
}
