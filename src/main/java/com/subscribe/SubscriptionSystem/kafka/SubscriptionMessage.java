package com.subscribe.SubscriptionSystem.kafka;

import org.luaj.vm2.ast.Str;

public class SubscriptionMessage {
    private String userId;
    private String bundleId;
    private int duration;
    private String operatorId;

    public int getDuration() {
        return duration;
    }

    public SubscriptionMessage(String userId, String bundleId, int duration, String operatorId) {
        this.userId = userId;
        this.bundleId = bundleId;
        this.duration = duration;
        this.operatorId = operatorId;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public SubscriptionMessage(String userId, String bundleId, String operatorId) {
        this.userId = userId;
        this.bundleId = bundleId;
        this.operatorId = operatorId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

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
