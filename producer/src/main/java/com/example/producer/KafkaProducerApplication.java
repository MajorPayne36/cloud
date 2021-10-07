package com.example.producer;

import com.example.producer.data.Payment;
import com.example.producer.service.ProducerService;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableScheduling
public class KafkaProducerApplication {
    private ProducerService service;

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class, args);
    }

    @PostMapping
    public void send(@RequestBody Payment payment) {
        service.send(payment);
    }

    @Bean // Kafka Admin
    public NewTopic messagesTopic() {
        return new NewTopic("payments", 3, (short) 2);
    }
}
