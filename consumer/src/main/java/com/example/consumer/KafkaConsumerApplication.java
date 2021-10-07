package com.example.consumer;

import com.example.consumer.data.Payment;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.NonNull;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@SpringBootApplication
@RequiredArgsConstructor
public class KafkaConsumerApplication {
    private final Log logger = LogFactory.getLog(this.getClass());
    private final String TOTOPIC = "other.payments";
    private final String TOPIC = "payments";
    private final String CONSUMER = "consumer";
    private final String KEY = "consumer.payment";
    private final KafkaTemplate<String, Payment> template;

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerApplication.class, args);
    }

    /**
     * Collect messages from topic "payments" and send to other topic
     *
     * @param message current message from topic
     */
    @KafkaListener(groupId = CONSUMER, topics = TOPIC)
    public void listen(Payment message) {
        logger.info(message);
        send(message);
    }

    /**
     * Send the payment to topic "other.payments"
     *
     * @param payment which we should to send
     */
    public void send(Payment payment) {
        final ListenableFuture<SendResult<String, Payment>> future = template.send(
                new ProducerRecord<>(TOTOPIC, KEY, payment));
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(@NonNull Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onSuccess(SendResult<String, Payment> result) {
                logger.info(result);
            }
        });
    }
}
