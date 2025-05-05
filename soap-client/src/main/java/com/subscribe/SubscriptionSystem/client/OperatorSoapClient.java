package com.subscribe.SubscriptionSystem.client;

import com.subscribe.SubscriptionSystem.soap.ws.ActivateBundleRequest;
import com.subscribe.SubscriptionSystem.soap.ws.ActivateBundleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
public class OperatorSoapClient {

    @Autowired
    private WebServiceTemplate webServiceTemplate;
    private static final Logger log = LoggerFactory.getLogger(OperatorSoapClient.class);

    public ActivateBundleResponse activateSubscription(String id) {
        ActivateBundleRequest request = new ActivateBundleRequest();
        request.setSubscriptionId(id);

        log.info("Sending ActivateBundleRequest with subscriptionId={}", id);

        try {
            ActivateBundleResponse response = (ActivateBundleResponse) webServiceTemplate.marshalSendAndReceive(
                    "http://soap-service:8081/ws",
                    request,
                    new SoapActionCallback("http://subscribe.com/operator/ws/ActivateBundleRequest")
            );

            log.info("Received response: {}", response);
            return response;

        } catch (Exception e) {
            log.error("Error occurred while sending SOAP request", e);
            throw e;
        }
    }

}
