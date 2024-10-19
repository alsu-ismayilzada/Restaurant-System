package com.example.restaurantsystem.service.impl;

import com.example.restaurantsystem.dto.response.UserResponse;
import com.example.restaurantsystem.dto.request.UserRequest;
import com.example.restaurantsystem.entity.User;
import com.example.restaurantsystem.mapper.UserMapper;
import com.example.restaurantsystem.repository.UserRepository;
import com.example.restaurantsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse saveUser(UserRequest user) {
        var userEntity = userRepository.save(userMapper.toUserEntity(user));
        return userMapper.toUserDto(userEntity);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse findUserResponseById(Integer id) {
        return userMapper.toUserDto(findById(id));
    }

    @Override
    public List<UserResponse> findAll(int page, int count) {
        Page<User> all = userRepository.findAll(PageRequest.of(page,count));
        return all.getContent()
                .stream().map(userMapper::toUserDto)
                .toList();
    }

    @Override
    public UserResponse updateUserById(Integer id, UserRequest request) {
        var user = findById(id);
        userMapper.updateUser(user,request);
        return userMapper.toUserDto(user);
    }

    public User findById(Integer id){
        return userRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User Not Found"));
    }
}
