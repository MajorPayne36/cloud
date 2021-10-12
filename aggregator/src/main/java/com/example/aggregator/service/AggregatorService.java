package com.example.aggregator.service;

import com.example.aggregator.dto.Payment;
import com.example.aggregator.dto.PaymentStats;
import com.example.aggregator.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AggregatorService {
    public List<PaymentStats> concat(List<Payment> payments, List<UserDto> users) {
        return payments.stream().map(p -> PaymentStats
                .builder()
                .id(p.getId())
                .amount(p.getAmount())
                .comment(p.getComment())
                .username(
                        users.stream().
                                filter(u -> u.getId() == p.getSenderId())
                                .collect(Collectors.toList())
                                .get(0).getUsername()
                ).build()).collect(Collectors.toList());
    }
}
