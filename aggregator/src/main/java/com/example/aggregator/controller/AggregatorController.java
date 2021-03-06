package com.example.aggregator.controller;

import com.example.aggregator.client.DataClient;
import com.example.aggregator.client.UserClient;
import com.example.aggregator.dto.PaymentStats;
import com.example.aggregator.dto.ResponseDto;
import com.example.aggregator.dto.UserDto;
import com.example.aggregator.service.AggregatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AggregatorController {
    private final DataClient dataClient;
    private final UserClient userClient;
    private final AggregatorService service;

    @GetMapping("/value")
    public ResponseDto value() {
        return dataClient.getValue();
    }

    @GetMapping("/payments")
    public List<PaymentStats> stats() {

        // Get last 1000 payments from data
        final var payments = dataClient.getList(1000);
        List<Long> usersId = new LinkedList<>();
        payments.forEach(p -> usersId.add(p.getSenderId()));

        // Get payments senders username from users
        final var users = userClient.getUsers(usersId);

        // Concat payments and return
        return service.concat(payments, users);
    }

    @GetMapping("/users")
    public List<UserDto> users() {
        return userClient.getUsers();
    }

}
