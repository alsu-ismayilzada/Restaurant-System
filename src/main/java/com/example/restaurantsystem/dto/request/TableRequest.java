package com.example.restaurantsystem.dto.request;

import com.example.restaurantsystem.repository.TableStatus;
import lombok.Data;

@Data
public class TableRequest {
    Integer capacity;
    TableStatus status;
}
