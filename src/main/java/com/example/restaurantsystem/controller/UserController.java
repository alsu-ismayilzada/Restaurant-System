package com.example.restaurantsystem.controller;

import com.example.restaurantsystem.dto.UserDto;
import com.example.restaurantsystem.management.UserManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserManager userManager;

    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }
    @PostMapping()
    public void addUser(@RequestBody UserDto user){
        userManager.addUser(user);
    }
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id){
        userManager.deleteById(id);
    }
    @GetMapping("{id}")
    public UserDto getById(@PathVariable Integer id){
       return userManager.getById(id);
    }
    @GetMapping
    public List<UserDto> getAll(){
        return userManager.getAll();
    }
}
