package com.example.restaurantsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserRequest {

    String name;
    @NotBlank
    @NotNull
    String address;
    @NotBlank
    @NotNull
    String password;

    Integer contact;
}
