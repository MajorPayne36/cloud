package com.example.users.service;

import com.example.users.dto.UserDto;
import com.example.users.entity.User;
import com.example.users.entity.mapper.UserMapper;
import com.example.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public List<User> getUsers() {
        return repository.getAll();
    }

    public List<UserDto> getUsers(LinkedList<Long> usersId) {
        return usersId.stream().map(repository::getById).map(mapper::toUserDto).collect(Collectors.toList());
    }
}
