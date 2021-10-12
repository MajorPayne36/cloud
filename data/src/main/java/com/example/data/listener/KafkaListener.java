package com.example.data.listener;

import com.example.data.data.Payment;
import com.example.data.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaListener {
    private final Log logger = LogFactory.getLog(this.getClass());
    private final PaymentRepository repository;

    @org.springframework.kafka.annotation.KafkaListener(groupId = "data.consumers", topics = "other.payments")
    public void listen(Payment message, Acknowledgment acknowledgment) {
        logger.info(message);
        repository.save(message);
        acknowledgment.acknowledge();
    }
}
