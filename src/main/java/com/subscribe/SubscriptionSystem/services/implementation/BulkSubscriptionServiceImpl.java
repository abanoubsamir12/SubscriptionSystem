package com.subscribe.SubscriptionSystem.services.implementation;

import com.subscribe.SubscriptionSystem.enums.SubscriptionStatus;
import com.subscribe.SubscriptionSystem.mappers.SubscriptionMapper;
import com.subscribe.SubscriptionSystem.model.Bundle;
import com.subscribe.SubscriptionSystem.model.Subscription;
import com.subscribe.SubscriptionSystem.model.User;
import com.subscribe.SubscriptionSystem.repository.BundleRepository;
import com.subscribe.SubscriptionSystem.repository.SubscriptionRepository;
import com.subscribe.SubscriptionSystem.repository.UserRepository;
import com.subscribe.SubscriptionSystem.services.BulkSubscriptionService;
import com.subscribe.SubscriptionSystem.soap.client.OperatorSoapClient;
import com.subscribe.SubscriptionSystem.soap.ws.ActivateBundleResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class BulkSubscriptionServiceImpl implements BulkSubscriptionService {

    // Repositories and clients injected via Spring's dependency injection
    private static final Logger log = LoggerFactory.getLogger(BulkSubscriptionServiceImpl.class);
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private BundleRepository bundleRepository;

    @Autowired
    private OperatorSoapClient operatorSoapClient;

    @Autowired
    private SubscriptionMapper subscriptionMapper;

    @Autowired
    private UserRepository userRepository;

    /**
     * Asynchronously subscribes a list of users to a bundle and activates the subscription via SOAP.
     *
     * @param usersId  list of user IDs to subscribe
     * @param bundleId bundle ID to subscribe users to
     */
    @Override
    public void bulkSubscribe(List<String> usersId, String bundleId) {
        // Find the bundle or throw if not found
        Bundle bundle = bundleRepository.findById(bundleId)
                .orElseThrow(() -> new NoSuchElementException("This bundle does not exist"));

        // Submit each user to be processed asynchronously
        usersId.forEach(userId -> processSubscriptionAsync(userId, bundle));
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

            // Call the SOAP client to activate the subscription
            ActivateBundleResponse response = operatorSoapClient.activateSubscription(sub.getId());

            // Log or print activation success (optional: you can check response success here)
            log.info("Subscribed: " + userId);

        } catch (Exception e) {
            log.info(  e.getMessage());
        }
    }
}
