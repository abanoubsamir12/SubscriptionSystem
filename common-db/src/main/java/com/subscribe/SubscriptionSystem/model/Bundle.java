package com.subscribe.SubscriptionSystem.model;


import com.subscribe.SubscriptionSystem.enums.BucketType;
import com.subscribe.SubscriptionSystem.enums.BucketUnit;
import org.springframework.data.aerospike.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.*;


@Document(collection = "bundles")

public class Bundle {
    @Id
    private String id;

    private String code;
    private List<Bucket> buckets;
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

    public List<Bucket> getBuckets() {
        return buckets;
    }

    public void setBuckets(List<Bucket> buckets) {
        this.buckets = buckets;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    public static class Bucket{
        @Id
        String id;
        private BucketType type;
        private BucketUnit unit;
        private  int Quantity;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Bucket(){}
        public Bucket(BucketType type, BucketUnit unit, int quantity) {
            this.id= UUID.randomUUID().toString();
            this.type = type;
            this.unit = unit;
            Quantity = quantity;
        }

        public BucketType getType() {
            return type;
        }

        public void setType(BucketType type) {
            this.type = type;
        }

        public BucketUnit getUnit() {
            return unit;
        }

        public void setUnit(BucketUnit unit) {
            this.unit = unit;
        }

        public int getQuantity() {
            return Quantity;
        }

        public void setQuantity(int quantity) {
            Quantity = quantity;
        }
    }

}
