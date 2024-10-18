package com.example.restaurantsystem.mapper;

import com.example.restaurantsystem.dto.response.UserResponse;
import com.example.restaurantsystem.dto.request.UserRequest;
import com.example.restaurantsystem.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserDto(User user);
    User toUserEntity(UserRequest userDto);
    void updateUser(@MappingTarget User user, UserRequest userDto);

}
