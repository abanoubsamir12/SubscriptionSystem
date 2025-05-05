package com.subscribe.SubscriptionSystem.kafka;

import com.subscribe.SubscriptionSystem.client.OperatorSoapClient;
import com.subscribe.SubscriptionSystem.enums.SubscriptionStatus;
import com.subscribe.SubscriptionSystem.model.Subscription;
import com.subscribe.SubscriptionSystem.model.User;
import com.subscribe.SubscriptionSystem.repository.BundleRepository;
import com.subscribe.SubscriptionSystem.repository.SubscriptionRepository;
import com.subscribe.SubscriptionSystem.repository.UserRepository;
import com.subscribe.SubscriptionSystem.services.implementation.BulkSubscriptionServiceImpl;
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
    public void consume(com.subscribe.SubscriptionSystem.kafka.SubscriptionMessage message) {
        try {

            // Create a new Subscription object and populate it
            Subscription sub = subscriptionRepository.findById(message.getSubscription_id())
                    .orElseThrow(()->new NoSuchElementException("subscription does not exit"));
            // Call external SOAP service to activate the subscription
            operatorSoapClient.activateSubscription(sub.getId());

        } catch (Exception e) {
            // Log any error that occurs during processing
            log.error("Kafka processing failed: {}", e.getMessage());
        }
    }
}
