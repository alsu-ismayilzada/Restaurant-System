package com.example.restaurantsystem.controller;

import com.example.restaurantsystem.dto.response.UserResponse;
import com.example.restaurantsystem.dto.request.UserRequest;
import com.example.restaurantsystem.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    private final UserServiceImpl userManager;

    public UserController(UserServiceImpl userManager) {
        this.userManager = userManager;
    }
    @PostMapping()
    public void addUser(@RequestBody UserRequest user){
        userManager.addUser(user);
        log.info("add process executed");
    }
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id){
        userManager.deleteById(id);
        log.info("delete process executed");
    }
    @GetMapping("{id}")
    public UserResponse getById(@PathVariable Integer id){
       return userManager.getById(id);
    }
    @GetMapping
    public List<UserResponse> getAll(@RequestParam(value = "page") int page, @RequestParam(value = "count") int count){
        return userManager.getAll(page,count);
    }
}
