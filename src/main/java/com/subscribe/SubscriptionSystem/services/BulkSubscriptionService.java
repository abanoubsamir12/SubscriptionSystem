package com.subscribe.SubscriptionSystem.services;

import com.subscribe.SubscriptionSystem.model.User;
import jakarta.jws.soap.SOAPBinding;
import java.util.List;
public interface BulkSubscriptionService {
    public void bulkSubscribe(List<String > usersId, String bundleId);
}
