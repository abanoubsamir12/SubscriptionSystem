package com.subscribe.SubscriptionSystem.soap.endpoints;

import com.aerospike.client.Log;
import com.subscribe.SubscriptionSystem.enums.SubscriptionStatus;
import com.subscribe.SubscriptionSystem.model.Subscription;
import com.subscribe.SubscriptionSystem.repository.SubscriptionRepository;
import com.subscribe.SubscriptionSystem.soap.ws.ActivateBundleRequest;
import com.subscribe.SubscriptionSystem.soap.ws.ActivateBundleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.NoSuchElementException;
@Endpoint
public class OperatorEndpoint {
    private static final String NAMESPACE_URI = "http://subscribe.com/operator/ws";
    @Autowired
    SubscriptionRepository subscriptionRepository;
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ActivateBundleRequest")
    @ResponsePayload
    public ActivateBundleResponse activate(@RequestPayload ActivateBundleRequest request) {
        ActivateBundleResponse response = new ActivateBundleResponse();
        try {
            Log.info("SOAP >>> Endpoint reached");
            Subscription curr = subscriptionRepository.findById(request.getSubscriptionId())
                    .orElseThrow(() -> new NoSuchElementException("Subscription not exits"));
            curr.setStatus(SubscriptionStatus.ACTIVE);
            subscriptionRepository.save(curr);
            response.setSuccess(true);
            response.setMessage("Subscription activated successfully");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error: " + e.getMessage());
        }
        return response;
    }
}
