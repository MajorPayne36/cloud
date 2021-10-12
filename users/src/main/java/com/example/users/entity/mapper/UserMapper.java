package com.example.users.entity.mapper;

import com.example.users.dto.UserDto;
import com.example.users.entity.User;

public class UserMapper {
    public UserDto toUserDto(User user){
        return new UserDto(user.getId(), user.getUsername());
    }
}
