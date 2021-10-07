package com.example.data.listeners;

import com.example.data.data.Payment;
import com.example.data.repository.PaymentRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class KafkaListeners {
    private final Log logger = LogFactory.getLog(this.getClass());
    PaymentRepository repository;

    @KafkaListener(groupId = "data.consumers", topics = "other.payments")
    public void listen(Payment message, ConsumerRecord<String, Payment> record, Acknowledgment acknowledgment) {
        logger.info(message);
        acknowledgment.acknowledge();
        repository.save(message);
    }
}