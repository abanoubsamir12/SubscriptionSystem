package com.subscribe.SubscriptionSystem.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class SubscriptionKafkaProducer {
    @Autowired
    private KafkaTemplate<String, SubscriptionMessage> kafkaTemplate;

    public void send(SubscriptionMessage message) {
        kafkaTemplate.send("subscription.create",message.getUserId(), message);
    }
}
