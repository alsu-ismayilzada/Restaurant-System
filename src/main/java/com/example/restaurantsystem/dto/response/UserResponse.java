package com.example.restaurantsystem.dto.response;

import lombok.Data;

@Data
public class UserResponse{

        Integer id;
        String name;
        String address;
        String password;
        Integer contact;
}
