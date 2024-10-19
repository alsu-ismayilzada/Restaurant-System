package com.example.restaurantsystem.dto.response;

import com.example.restaurantsystem.enums.TableStatus;
import lombok.Data;

@Data
public class TableResponse{

    Long id;
    Integer capacity;
    TableStatus status;
}
