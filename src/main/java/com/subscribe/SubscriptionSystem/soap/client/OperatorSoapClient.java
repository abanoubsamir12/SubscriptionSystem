package com.subscribe.SubscriptionSystem.soap.client;

import com.subscribe.SubscriptionSystem.soap.ws.ActivateBundleRequest;
import com.subscribe.SubscriptionSystem.soap.ws.ActivateBundleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;

@Component
public class OperatorSoapClient {

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    public ActivateBundleResponse activateSubscription(String id) {
        ActivateBundleRequest request = new ActivateBundleRequest();
        request.setSubscriptionId(id);

        return (ActivateBundleResponse) webServiceTemplate.marshalSendAndReceive(
                "http://localhost:8080/ws",
                request,
                new SoapActionCallback("http://subscribe.com/operator/ws/ActivateBundleRequest")
        );
    }
}
