package com.example.users.controller;

import com.example.users.dto.UserDto;
import com.example.users.entity.User;
import com.example.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UserService service;
    private final Log logger = LogFactory.getLog(this.getClass());

    @GetMapping("/users")
    public List<User> endpoint(@RequestHeader Optional<String> authorization) {
        logger.info("request");
        // Return all users
        return service.getUsers();
    }

    @PostMapping("/users/list")
    public List<UserDto> endpoint(@RequestBody List<Long> usersId) {
        logger.info("users list request");
        // Return users by id collection
        return service.getUsers(usersId);
    }


}
