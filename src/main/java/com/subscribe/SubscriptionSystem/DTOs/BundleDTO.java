package com.subscribe.SubscriptionSystem.DTOs;

import com.subscribe.SubscriptionSystem.enums.BucketType;
import com.subscribe.SubscriptionSystem.enums.BucketUnit;
import com.subscribe.SubscriptionSystem.model.Bundle.Bucket;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.List;
import java.util.Map;


public class BundleDTO {
    private String id; // Optional for creation, useful for updates

    @NotBlank(message = "Bundle code is required")
    private String code;


    private List<BucketDTO> buckets;

    public List<BucketDTO> getBuckets() {
        return buckets;
    }

    public void setBuckets(List<BucketDTO> buckets) {
        this.buckets = buckets;
    }
    @Positive(message = "Period must be greater than 0")
    private int period;

    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String  getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public BundleDTO(String id, String code,List<BucketDTO>buckets, int period, String createdAt) {
        this.id = id;
        this.code = code;
        this.buckets = buckets;
        this.period = period;
        this.createdAt = createdAt;
    }


}

