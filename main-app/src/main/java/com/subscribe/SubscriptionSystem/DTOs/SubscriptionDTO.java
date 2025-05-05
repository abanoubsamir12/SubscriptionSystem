package com.subscribe.SubscriptionSystem.DTOs;
import com.subscribe.SubscriptionSystem.enums.SubscriptionStatus;
import jakarta.validation.constraints.NotNull;

public class SubscriptionDTO {



    private String id;
 private String startAt;

    private String endAt;

    @NotNull(message = "User ID is required")
    private String userId;

    @NotNull(message = "bundle ID is required")
    private String bundleId;
    @NotNull(message = "operator ID is required")
    private String operatorId;

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    private SubscriptionStatus status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
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

    public SubscriptionStatus getStatus() {
        return status;
    }

    public void setStatus(SubscriptionStatus status) {
        this.status = status;
    }

    public SubscriptionDTO() {
    }

    public SubscriptionDTO(String id, String startAt, String endAt, String userId, String bundleId, SubscriptionStatus status) {
        this.id = id;
        this.startAt = startAt;
        this.endAt = endAt;
        this.userId = userId;
        this.bundleId = bundleId;
        this.status = status;
    }
}
