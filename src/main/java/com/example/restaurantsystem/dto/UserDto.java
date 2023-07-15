package com.example.restaurantsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserDto(
        String name,
        @NotBlank
        @NotNull
        @NotEmpty
        String address,
        @NotBlank
        @NotNull
        @NotEmpty
        String password,

        Integer contact
) {
}
