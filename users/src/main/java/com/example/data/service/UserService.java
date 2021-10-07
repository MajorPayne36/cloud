package com.example.data.service;

import com.example.data.dto.UserDto;
import com.example.data.entity.User;
import com.example.data.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepository repository;

    public List<UserDto> getUsers() {
        final List<UserDto> users = new ArrayList<>();
        repository.getAll().forEach(v -> users.add(new UserDto(v.getId() + "", v.getUsername())));
        return  users;
    }
}
