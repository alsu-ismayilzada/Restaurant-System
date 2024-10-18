package com.example.repository;

public enum TableStatus {

    EMPTY("Empty"), FULL("Full");

    String statusText;

    TableStatus(String statusText) {
        this.statusText = statusText;
    }

    public String getStatusText() {
        return statusText;
    }
}
