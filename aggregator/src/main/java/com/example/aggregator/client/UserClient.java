package com.example.aggregator.client;

import com.example.aggregator.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "users")
public interface UserClient {
    @PostMapping("/users/list")
    List<UserDto> getUsers(@RequestBody List<Long> usersId);

    @GetMapping("/users")
    List<UserDto> getUsers();
}
