package com.subscribe.SubscriptionSystem.services;

import com.subscribe.SubscriptionSystem.DTOs.SubscriptionDTO;
import com.subscribe.SubscriptionSystem.soap.ws.ActivateBundleResponse;

import java.util.List;

public interface SubscriptionService {
    public SubscriptionDTO create(SubscriptionDTO user);
    public SubscriptionDTO getById(String id);
    public List<SubscriptionDTO> getAll();
    public SubscriptionDTO update(String id, SubscriptionDTO dto);
    public ActivateBundleResponse tryActivateSubscription(String id);
    public void delete(String id);
}
