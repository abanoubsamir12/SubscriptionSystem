package com.subscribe.SubscriptionSystem.services.implementation;

import com.subscribe.SubscriptionSystem.DTOs.BulkSubscriptionRequest;
import com.subscribe.SubscriptionSystem.client.OperatorSoapClient;
import com.subscribe.SubscriptionSystem.enums.SubscriptionStatus;
import com.subscribe.SubscriptionSystem.kafka.SubscriptionKafkaProducer;
import com.subscribe.SubscriptionSystem.kafka.SubscriptionMessage;
import com.subscribe.SubscriptionSystem.mappers.BundleMapper;
import com.subscribe.SubscriptionSystem.mappers.SubscriptionMapper;
import com.subscribe.SubscriptionSystem.model.Bundle;
import com.subscribe.SubscriptionSystem.model.Operator;
import com.subscribe.SubscriptionSystem.model.Subscription;
import com.subscribe.SubscriptionSystem.model.User;
import com.subscribe.SubscriptionSystem.repository.BundleRepository;
import com.subscribe.SubscriptionSystem.repository.OperatorRepository;
import com.subscribe.SubscriptionSystem.repository.SubscriptionRepository;
import com.subscribe.SubscriptionSystem.repository.UserRepository;
import com.subscribe.SubscriptionSystem.services.BulkSubscriptionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.NoSuchElementException;

@Service
public class BulkSubscriptionServiceImpl implements BulkSubscriptionService {

    // Repositories and clients injected via Spring's dependency injection
    private static final Logger log = LoggerFactory.getLogger(BulkSubscriptionServiceImpl.class);
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private BundleRepository bundleRepository;
    @Autowired
    BundleMapper bundleMapper;

    @Autowired
    SubscriptionKafkaProducer kafkaProducer;

    @Autowired
    private OperatorSoapClient operatorSoapClient;

    @Autowired
    private SubscriptionMapper subscriptionMapper;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OperatorRepository operatorRepository;

    /**
     * Asynchronously subscribes a list of users to a bundle and activates the subscription via SOAP.
     *
     * @param BulkSubscriptionRequest request
     */
    @Override
    public void bulkSubscribe(BulkSubscriptionRequest request) {
        // Find the bundle or throw if not found
        Bundle bundle = bundleRepository.findById(request.getBundleId())
                .orElseThrow(() -> new NoSuchElementException("This bundle does not exist"));
        Operator operator = operatorRepository.findById(request.getOperatorId())
                .orElseThrow(()-> new NoSuchElementException("This operator does not exist"));


        // Submit each user to be processed asynchronously
        //usersId.forEach(userId -> processSubscriptionAsync(userId, bundle));

        // Send each userId to Kafka
        request.getUsersId().forEach(userId -> {
             processSubscriptionAsync(userId,bundle);

        });
    }

    @Async("subscriptionExecutor")
    private void processSubscriptionAsync(String userId, Bundle bundle) {
        try {
            // For each user, submit a subscription task to the executor
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new NoSuchElementException("User (" + userId + ") does not exist"));

            // Create a new subscription for the user
            Subscription sub = new Subscription();
            sub.setUser_id(userId);
            sub.setBundle_id(bundle.getId());
            sub.setStartAt(Instant.now().toString()); // Current time
            sub.setEndAt(Instant.now().plusSeconds(bundle.getPeriod()).toString()); // End time based on bundle period
            sub.setStatus(SubscriptionStatus.PENDING);

            // Save the new subscription with PENDING status
            subscriptionRepository.save(sub);
            SubscriptionMessage message = new SubscriptionMessage(sub.getId());
            kafkaProducer.send(message);

            // Call the SOAP client to activate the subscription
            //Object response = operatorSoapClient.activateSubscription(sub.getId());

            // Log activation success
            log.info("Subscribed: " + userId);

        } catch (Exception e) {
            log.info(  e.getMessage());
        }
    }
}
