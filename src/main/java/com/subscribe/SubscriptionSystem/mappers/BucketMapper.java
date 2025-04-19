package com.subscribe.SubscriptionSystem.mappers;
import com.subscribe.SubscriptionSystem.DTOs.BucketDTO;
import com.subscribe.SubscriptionSystem.model.Bundle.Bucket;
import org.springframework.stereotype.Component;

@Component
public class BucketMapper {

    public BucketDTO toDTO( Bucket bucket) {
        BucketDTO dto = new BucketDTO();
        dto.setId(bucket.getId()); // You can inject this from the DB if Bucket has no ID field
        dto.setType(bucket.getType());
        dto.setUnit(bucket.getUnit());
        dto.setQuantity(bucket.getQuantity());
        return dto;
    }

    public Bucket toEntity(BucketDTO dto) {
        return new Bucket(dto.getType(), dto.getUnit(), dto.getQuantity());
    }
}
