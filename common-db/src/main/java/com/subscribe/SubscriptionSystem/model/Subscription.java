package com.subscribe.SubscriptionSystem.model;

import com.subscribe.SubscriptionSystem.enums.SubscriptionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.luaj.vm2.ast.Str;
import org.springframework.data.aerospike.mapping.Document;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.util.UUID;


@Document(collection = "subscriptions")
public class Subscription {
    @Id
    private String id;
    private String startAt;
    private String endAt;
    private String user_id;
    private String bundle_id;
    private String operator_id;

    public String getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }

    private SubscriptionStatus status;

    public Subscription() {
        id = UUID.randomUUID().toString();
    }

    public Subscription(String user_id, String bundle_id,String operator_id, SubscriptionStatus status) {
        this.user_id = user_id;
        this.bundle_id = bundle_id;
        this.status = status;
    }

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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBundle_id() {
        return bundle_id;
    }

    public void setBundle_id(String bundle_id) {
        this.bundle_id = bundle_id;
    }

    public SubscriptionStatus getStatus() {
        return status;
    }

    public void setStatus(SubscriptionStatus status) {
        this.status = status;
    }
}
