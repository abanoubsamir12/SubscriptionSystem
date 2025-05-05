package com.subscribe.SubscriptionSystem.services;

import com.subscribe.SubscriptionSystem.DTOs.BulkSubscriptionRequest;
public interface BulkSubscriptionService {
    public void bulkSubscribe(BulkSubscriptionRequest request);
}
