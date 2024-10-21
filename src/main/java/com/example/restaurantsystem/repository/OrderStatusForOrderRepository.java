package com.example.restaurantsystem.repository;

import com.example.restaurantsystem.entity.OrderStatusForOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusForOrderRepository extends JpaRepository<OrderStatusForOrder, Long> {
}
