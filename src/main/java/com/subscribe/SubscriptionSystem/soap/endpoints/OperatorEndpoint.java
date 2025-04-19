package com.subscribe.SubscriptionSystem.soap.endpoints;

import com.subscribe.SubscriptionSystem.enums.SubscriptionStatus;
import com.subscribe.SubscriptionSystem.mappers.SubscriptionMapper;
import com.subscribe.SubscriptionSystem.model.Subscription;
import com.subscribe.SubscriptionSystem.repository.SubscriptionRepository;
import com.subscribe.SubscriptionSystem.soap.ws.ActivateBundleRequest;
import com.subscribe.SubscriptionSystem.soap.ws.ActivateBundleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.NoSuchElementException;
@Endpoint
public class OperatorEndpoint {
    private static final String NAMESPACE_URI = "http://subscribe.com/operator/ws";

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ActivateBundleRequest")
    @ResponsePayload
    public ActivateBundleResponse activate(@RequestPayload ActivateBundleRequest request) {
        ActivateBundleResponse response = new ActivateBundleResponse();

        try {
            Subscription current = subscriptionRepository.findById(request.getSubscriptionId())
                    .orElseThrow(() -> new NoSuchElementException("Subscription not found"));

            current.setStatus(SubscriptionStatus.ACTIVE);
            subscriptionRepository.save(current);

            response.setSuccess(true);
            response.setMessage("Subscription activated successfully");
            return response;
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error: " + e.getMessage());
            return response;
        }
    }
}