package com.example.restaurantsystem.controller;

import com.example.restaurantsystem.dto.response.UserResponse;
import com.example.restaurantsystem.dto.request.UserRequest;
import com.example.restaurantsystem.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping()
    public UserResponse addUser(@RequestBody UserRequest user){
        return userService.saveUser(user);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id){
        userService.deleteById(id);
        log.info("delete process executed");
    }

    @GetMapping("{id}")
    public UserResponse getById(@PathVariable Integer id){
       return userService.findUserResponseById(id);
    }

    @GetMapping
    public List<UserResponse> getAll(@RequestParam(value = "page") int page, @RequestParam(value = "count") int count){
        return userService.findAll(page,count);
    }

    @PutMapping("/update/{userId}")
    public UserResponse update(@PathVariable Integer userId, @RequestBody UserRequest user){
        return userService.updateUserById(userId, user);
    }
}
