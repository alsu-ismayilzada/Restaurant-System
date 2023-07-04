package com.example.restaurantsystem.management;

import com.example.restaurantsystem.dto.UserDto;
import com.example.restaurantsystem.entity.User;
import com.example.restaurantsystem.mapper.UserMapper;
import com.example.restaurantsystem.repository.UserRepository;
import com.example.restaurantsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserManagement implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void addUser(UserDto user) {
        userRepository.save(userMapper.toUserEntity(user));
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto getById(Integer id) {
        return userRepository.findById(id)
                .stream().map(userMapper::toUserDto)
                .findFirst().get();
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream().map(userMapper::toUserDto)
                .toList();
    }
}
