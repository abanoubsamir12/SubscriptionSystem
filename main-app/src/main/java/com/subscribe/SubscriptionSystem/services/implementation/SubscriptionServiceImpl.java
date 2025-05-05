package com.subscribe.SubscriptionSystem.services.implementation;

import com.subscribe.SubscriptionSystem.DTOs.SubscriptionDTO;
import com.subscribe.SubscriptionSystem.client.OperatorSoapClient;
import com.subscribe.SubscriptionSystem.mappers.SubscriptionMapper;
import com.subscribe.SubscriptionSystem.model.Bundle;
import com.subscribe.SubscriptionSystem.model.Subscription;
import com.subscribe.SubscriptionSystem.model.User;
import com.subscribe.SubscriptionSystem.repository.BundleRepository;
import com.subscribe.SubscriptionSystem.repository.SubscriptionRepository;
import com.subscribe.SubscriptionSystem.repository.UserRepository;
import com.subscribe.SubscriptionSystem.services.SubscriptionService;
import com.subscribe.SubscriptionSystem.soap.ws.ActivateBundleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository repo;

    @Autowired
    private BundleRepository bundleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionMapper mapper;
    @Autowired
    private OperatorSoapClient operatorSoapClient;

    /**
     * Creates a new subscription.
     * Validates the user and bundle before creating the subscription.
     * Calculates the end date based on the bundle's period.
     */
    @Override
    public SubscriptionDTO create(SubscriptionDTO dto) {
        // Validate user
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new NoSuchElementException("No such user"));

        // Validate bundle
        Bundle bundle = bundleRepository.findById(dto.getBundleId())
                .orElseThrow(() -> new NoSuchElementException("Bundle not found"));

        // Convert DTO to entity and set calculated end date
        Subscription subscription = mapper.toEntity(dto);
        subscription.setEndAt(Instant.now().plusSeconds(bundle.getPeriod()).toString());

        // Save and return as DTO
        repo.save(subscription);
        return mapper.toDTO(subscription);
    }

    /**
     * Retrieves all subscriptions and maps them to DTOs.
     */
    @Override
    public List<SubscriptionDTO> getAll() {
        List<SubscriptionDTO> result = new ArrayList<>();
        repo.findAll().forEach(s -> result.add(mapper.toDTO(s)));
        return result;
    }

    /**
     * Updates an existing subscription.
     * Allows changes to bundle ID, start/end dates, and status.
     */
    @Override
    public SubscriptionDTO update(String id, SubscriptionDTO dto) {
        Subscription existing = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Subscription not found"));

        // Apply updates from DTO
        existing.setBundle_id(dto.getBundleId());
        existing.setEndAt(dto.getEndAt());
        existing.setStatus(dto.getStatus());
        existing.setStartAt(dto.getStartAt());

        return mapper.toDTO(repo.save(existing));
    }

    @Override
    public ActivateBundleResponse tryActivateSubscription(String id) {
        return operatorSoapClient.activateSubscription(id);
    }

    /**
     * Retrieves a single subscription by ID.
     */
    @Override
    public SubscriptionDTO getById(String id) {
        return mapper.toDTO(repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Subscription not found")));
    }

    /**
     * Deletes a subscription by ID.
     */
    @Override
    public void delete(String id) {
        repo.deleteById(id);
    }
}
