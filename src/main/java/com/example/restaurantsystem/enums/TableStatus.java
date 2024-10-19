package com.example.restaurantsystem.enums;

public enum TableStatus {

    EMPTY("Empty"), BOOKED("Booked");

    String statusText;

    TableStatus(String statusText) {
        this.statusText = statusText;
    }

    public String getStatusText() {
        return statusText;
    }
}
