package com.example.restaurantsystem.service.impl;

import com.example.restaurantsystem.dto.UserDto;
import com.example.restaurantsystem.entity.User;
import com.example.restaurantsystem.mapper.UserMapper;
import com.example.restaurantsystem.repository.UserRepository;
import com.example.restaurantsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserServiceImpl implements UserService {

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
    public List<UserDto> getAll(int page,int count) {
        Page<User> all = userRepository.findAll(PageRequest.of(page,count));
        return all.getContent()
                .stream().map(userMapper::toUserDto)
                .toList();
    }
}
