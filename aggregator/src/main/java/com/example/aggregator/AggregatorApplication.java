package com.example.aggregator;

import com.example.aggregator.client.DataClient;
import com.example.aggregator.client.UserClient;
import com.example.aggregator.dto.PaymentStats;
import com.example.aggregator.dto.ResponseDto;
import com.example.aggregator.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableFeignClients
@RestController("/api")
@RequiredArgsConstructor
@CommonsLog
public class AggregatorApplication {
    private final DiscoveryClient discoveryClient;
    private final DataClient dataClient;
    private final UserClient userClient;

    @GetMapping("/value")
    public ResponseDto value() {
//    final List<ServiceInstance> services = discoveryClient.getInstances("data");
//    logger.info(services.stream().map(o -> o.getUri().toString()).collect(Collectors.joining(", ")));
        return dataClient.getValue();
    }

    @GetMapping("/payments")
    public List<PaymentStats> stats() {

        // Get last 1000 payments from data
        final var payments = dataClient.getList(1000);
        LinkedList<Long> usersId = new LinkedList<>();
        payments.forEach(p -> {
            usersId.add(p.getSenderId());
        });

        // Get payments senders username from users
        final var users = userClient.getUsers(usersId);

        // Concat payments and return
        return payments.stream().map(p -> {
            return PaymentStats
                    .builder()
                    .id(p.getId())
                    .amount(p.getAmount())
                    .comment(p.getComment())
//                    .username(users.stream().map(u -> {
//                        if (u.getId() == p.getSenderId()) return u.getUsername();
//                        else return null;
//                    }).collect(Collectors.toList()).get(0))
//                    .build();
                    .username(users.stream().filter(u -> u.getId() == p.getSenderId()).collect(Collectors.toList()).get(0).getUsername())
                    .build();
        }).collect(Collectors.toList());
    }

    @GetMapping("/users")
    public List<UserDto> users() {
        return userClient.getUsers();
    }

    public static void main(String[] args) {
        SpringApplication.run(AggregatorApplication.class, args);
    }
}
