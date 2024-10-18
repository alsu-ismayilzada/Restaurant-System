package com.example.restaurantsystem.dto.response;

import com.example.restaurantsystem.repository.TableStatus;
import lombok.Data;

@Data
public class TableResponse{

    Integer capacity;
    TableStatus status;
}
