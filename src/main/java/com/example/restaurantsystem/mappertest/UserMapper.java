package com.example.restaurantsystem.mappertest;

import com.example.restaurantsystem.dto.UserDto;
import com.example.restaurantsystem.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
    User toUserEntity(UserDto userDto);

}
