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

    // Logger instance for logging events
    private static final Logger log = LoggerFactory.getLogger(BulkSubscriptionServiceImpl.class);

    // Injecting Subscription repository to persist subscription data
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    // Injecting User repository to fetch user information
    @Autowired
    private UserRepository userRepository;

    // Injecting Bundle repository to fetch bundle details
    @Autowired
    private BundleRepository bundleRepository;

    // Injecting SOAP client to activate subscriptions via external service
    @Autowired
    private OperatorSoapClient operatorSoapClient;

    /**
     * Kafka consumer method to listen for new subscription messages
     * from the "subscription.create" topic.
     */
    @KafkaListener(
            topics = "subscription.create",
            groupId = "subscription-group",
            containerFactory = "subscriptionListenerFactory"
    )
    public void consume(SubscriptionMessage message) {
        try {
            // Log the consumed Kafka message
         //   log.info("Consumed from Kafka: {}", message);

            // Retrieve the user from the database or throw if not found
            User user = userRepository.findById(message.getUserId())
                    .orElseThrow(() -> new NoSuchElementException("User not found"));



            // Create a new Subscription object and populate it
            Subscription sub = new Subscription();
            sub.setUser_id(user.getId());
            sub.setBundle_id(message.getBundleId());
            sub.setStartAt(Instant.now().toString()); // Start time is now
            sub.setEndAt(String.valueOf(Instant.now().plusSeconds(message.getDuration()))); // End time is based on bundle duration
            sub.setStatus(SubscriptionStatus.PENDING); // Set initial status
            sub.setOperator_id(message.getOperatorId()); // Set operator ID

            // Save the new subscription in the database
            subscriptionRepository.save(sub);

            // Call external SOAP service to activate the subscription
            operatorSoapClient.activateSubscription(sub.getId());

        } catch (Exception e) {
            // Log any error that occurs during processing
            log.error("Kafka processing failed: {}", e.getMessage());
        }
    }
}
