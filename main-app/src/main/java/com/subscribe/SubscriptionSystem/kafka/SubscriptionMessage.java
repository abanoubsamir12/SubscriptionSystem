package com.subscribe.SubscriptionSystem.kafka;

import org.luaj.vm2.ast.Str;

public class SubscriptionMessage {
    String subscription_id;

    public SubscriptionMessage(String subscription_id) {
        this.subscription_id = subscription_id;
    }

    public SubscriptionMessage() {
    }

    public String getSubscription_id() {
        return subscription_id;
    }

    public void setSubscription_id(String subscription_id) {
        this.subscription_id = subscription_id;
    }
}
