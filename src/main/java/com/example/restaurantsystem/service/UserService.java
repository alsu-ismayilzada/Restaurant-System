package com.example.restaurantsystem.service;

import com.example.restaurantsystem.dto.UserDto;
import com.example.restaurantsystem.entity.User;

import java.util.List;

public interface UserService {


    void addUser(UserDto user);
    void deleteById(Integer id);
    UserDto getById(Integer id);
    List<UserDto> getAll(int page,int count);
}
