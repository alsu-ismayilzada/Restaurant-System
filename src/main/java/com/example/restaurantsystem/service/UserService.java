package com.example.restaurantsystem.service;

import com.example.restaurantsystem.dto.response.UserResponse;
import com.example.restaurantsystem.dto.request.UserRequest;

import java.util.List;

public interface UserService {


    void addUser(UserRequest user);
    void deleteById(Integer id);
    UserResponse getById(Integer id);
    List<UserResponse> getAll(int page, int count);
}
