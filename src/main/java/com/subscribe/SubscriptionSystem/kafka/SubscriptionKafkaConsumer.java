package com.subscribe.SubscriptionSystem.kafka;

import com.subscribe.SubscriptionSystem.enums.SubscriptionStatus;
import com.subscribe.SubscriptionSystem.model.Bundle;
import com.subscribe.SubscriptionSystem.model.Subscription;
import com.subscribe.SubscriptionSystem.model.User;
import com.subscribe.SubscriptionSystem.repository.BundleRepository;
import com.subscribe.SubscriptionSystem.repository.SubscriptionRepository;
import com.subscribe.SubscriptionSystem.repository.UserRepository;
import com.subscribe.SubscriptionSystem.services.implementation.BulkSubscriptionServiceImpl;
import com.subscribe.SubscriptionSystem.soap.client.OperatorSoapClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.NoSuchElementException;

@Component

public class SubscriptionKafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(BulkSubscriptionServiceImpl.class);

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BundleRepository bundleRepository;

    @Autowired
    private OperatorSoapClient operatorSoapClient;

    @KafkaListener(topics = "subscription.create", groupId = "subscription-group", containerFactory = "subscriptionListenerFactory")
    public void consume(SubscriptionMessage message) {

        try {
            log.info("Consumed from Kafka: {}", message);

            User user = userRepository.findById(message.getUserId())
                    .orElseThrow(() -> new NoSuchElementException("User not found"));

            Bundle bundle = bundleRepository.findById(message.getBundleId())
                    .orElseThrow(() -> new NoSuchElementException("Bundle not found"));

            Subscription sub = new Subscription();
            sub.setUser_id(user.getId());
            sub.setBundle_id(bundle.getId());
            sub.setStartAt(Instant.now().toString());
            sub.setEndAt(Instant.now().plusSeconds(bundle.getPeriod()).toString());
            sub.setStatus(SubscriptionStatus.PENDING);

            subscriptionRepository.save(sub);

            operatorSoapClient.activateSubscription(sub.getId());

        } catch (Exception e) {
            log.error("Kafka processing failed: {}", e.getMessage());
        }
    }
}
