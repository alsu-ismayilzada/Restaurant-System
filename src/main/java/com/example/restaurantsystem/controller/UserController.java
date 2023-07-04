package com.example.restaurantsystem.controller;

import com.example.restaurantsystem.dto.UserDto;
import com.example.restaurantsystem.management.UserManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    private final UserManager userManager;

    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }
    @PostMapping()
    public void addUser(@RequestBody UserDto user){
        userManager.addUser(user);
        log.info("add process executed");
    }
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id){
        userManager.deleteById(id);
        log.info("delete process executed");
    }
    @GetMapping("{id}")
    public UserDto getById(@PathVariable Integer id){
       return userManager.getById(id);
    }
    @GetMapping
    public List<UserDto> getAll(@RequestParam(value = "page") int page,@RequestParam(value = "count") int count){
        return userManager.getAll(page,count);
    }
}
