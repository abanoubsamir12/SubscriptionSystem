package com.subscribe.SubscriptionSystem.services;

import com.subscribe.SubscriptionSystem.DTOs.BulkSubscriptionRequest;
import com.subscribe.SubscriptionSystem.model.User;
import jakarta.jws.soap.SOAPBinding;
import java.util.List;
public interface BulkSubscriptionService {
    public void bulkSubscribe(BulkSubscriptionRequest request);
}
