//package com.example.restaurantsystem.mappertest;
//
//import com.example.restaurantsystem.dto.UserDto;
//import com.example.restaurantsystem.entity.User;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import static com.example.restaurantsystem.entity.repository.Role.USER;
//
//class UserMapperTest {
//
//    private final UserMapper userMapper= new UserMapperImpl();
//    @Test
//    void toUserDto() {
//        //given
//        User user= new User(1,"Alsu","qax","123",123,USER);
//        UserDto expected = new UserDto("Alsu","qax","123",123);
//        //when
//        UserDto actual = userMapper.toUserDto(user);
//        //then
//        Assertions.assertThat(actual).isEqualTo(expected);
//
//
//    }
//
//    @Test
//    void toUserEntity() {
//        //given
//        UserDto userDto = new UserDto("Alsu","qax","123",123);
//        User expected= new User(null,"Alsu","qax","123",123,null);
//        //when
//        User actual = userMapper.toUserEntity(userDto);
//        //then
//        Assertions.assertThat(actual).isEqualTo(expected);
//    }
//}