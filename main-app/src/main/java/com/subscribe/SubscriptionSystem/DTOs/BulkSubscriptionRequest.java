package com.subscribe.SubscriptionSystem.DTOs;
import java.util.List;

public class BulkSubscriptionRequest {
    String bundleId;
    String operatorId;
    List<String>usersId;

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public BulkSubscriptionRequest(String bundleId, List<String> usersId) {
        this.bundleId = bundleId;
        this.usersId = usersId;
    }

    public BulkSubscriptionRequest() {
    }

    public String getBundleId() {
        return bundleId;
    }

    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }

    public List<String> getUsersId() {
        return usersId;
    }

    public void setUsersId(List<String> usersId) {
        this.usersId = usersId;
    }
}
