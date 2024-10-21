package com.example.restaurantsystem.enums;

import lombok.Getter;

@Getter
public enum OrderState {

    NEW,
    CREATED,
    PROCESSING,
    SHIPPED,
    DELIVERED,
    CANCELLED
}
