package com.example.users;

import com.example.users.entity.User;
import com.example.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequiredArgsConstructor
public class UsersApplication {
    private final UserService service;
    private final Log logger = LogFactory.getLog(this.getClass());

    @GetMapping("/users")
    public List<User> endpoint(@RequestHeader Optional<String> authorization) {
        logger.info("request");
        // Return all users
        return service.getUsers();
    }

    @PostMapping("/users/list")
    public List<User> endpoint(@RequestBody LinkedList<Long> usersId) {
        logger.info("users list request");
        // Return users by id collection
        return service.getUsers(usersId);
    }

    public static void main(String[] args) {
        SpringApplication.run(UsersApplication.class, args);
    }

}