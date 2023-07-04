package com.example.restaurantsystem.service;

import com.example.restaurantsystem.entity.User;

import java.util.List;

public interface UserService {


    void addUser(User user);
    void deleteById(Integer id);
    User getById(Integer id);
    List<User> getAll();
}
