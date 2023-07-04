package com.example.restaurantsystem.dto;

public record UserDto(
        String name,
        String address,
        String password,
        String contact
) {
}
