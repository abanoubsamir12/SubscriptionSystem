package com.subscribe.SubscriptionSystem.kafka;

import org.luaj.vm2.ast.Str;

public class SubscriptionMessage {
    private String userId;
    private String bundleId;

    public SubscriptionMessage() {
    }

    public SubscriptionMessage(String userId, String bundleId) {
        this.userId = userId;
        this.bundleId = bundleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBundleId() {
        return bundleId;
    }

    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }
}
