package com.example.producer;

import com.example.producer.data.Payment;
import com.example.producer.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequiredArgsConstructor
public class KafkaProducerApplication {
    private final ProducerService service;

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class, args);
    }

    @PostMapping("/payments")
    public void send(@RequestBody Payment payment) {
        service.send(payment);
    }
}
