package com.subscribe.SubscriptionSystem.DTOs;


import com.subscribe.SubscriptionSystem.enums.BucketType;
import com.subscribe.SubscriptionSystem.enums.BucketUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.luaj.vm2.ast.Str;

@Data
public class BucketDTO {
    private String id;
    private BucketType type;
    private BucketUnit unit;
    private int quantity;

    public BucketDTO(String id, BucketType type, BucketUnit unit, int quantity) {
        this.id=id;
        this.type = type;
        this.unit = unit;
        this.quantity = quantity;
    }
    public BucketDTO(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
