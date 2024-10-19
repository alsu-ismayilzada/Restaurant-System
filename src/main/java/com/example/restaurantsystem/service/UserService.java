package com.example.restaurantsystem.service;

import com.example.restaurantsystem.dto.response.UserResponse;
import com.example.restaurantsystem.dto.request.UserRequest;
import com.example.restaurantsystem.entity.User;

import java.util.List;

public interface UserService {


    UserResponse saveUser(UserRequest user);
    void deleteById(Integer id);
    UserResponse findUserResponseById(Integer id);
    List<UserResponse> findAll(int page, int count);
    UserResponse updateUserById(Integer id, UserRequest user);
    User findById(Integer id);
}
