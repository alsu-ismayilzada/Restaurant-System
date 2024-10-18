package com.example.restaurantsystem.dto.request;

import com.example.repository.TableStatus;
import lombok.Data;

@Data
public class TableRequest {
    Integer capacity;
    TableStatus status;
}
