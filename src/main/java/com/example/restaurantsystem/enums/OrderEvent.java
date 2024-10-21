package com.example.restaurantsystem.enums;


import lombok.Getter;

@Getter
public enum OrderEvent {

    CREATE,
    PROCESS,
    SHIP,
    DELIVER,
    CANCEL
}