package com.subscribe.SubscriptionSystem.mappers;

import com.subscribe.SubscriptionSystem.DTOs.BucketDTO;
import com.subscribe.SubscriptionSystem.DTOs.BundleDTO;

import com.subscribe.SubscriptionSystem.model.Bundle.Bucket;
import com.subscribe.SubscriptionSystem.model.Bundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class BundleMapper {
    @Autowired
    BucketMapper bucketMapper;
    public Bundle toEntity(BundleDTO dto) {
        if (dto == null) return null;



        Bundle bundle = new Bundle();
        bundle.setId(dto.getId() != null ? dto.getId() : UUID.randomUUID().toString());
        bundle.setCode(dto.getCode());
        bundle.setPeriod(dto.getPeriod());
        bundle.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt().toString() : Instant.now().toString());
        List<Bucket> bucketList = new ArrayList<>();
        for(BucketDTO b : dto.getBuckets())
        {
            bucketList.add(bucketMapper.toEntity(b));
        }
        bundle.setBuckets(bucketList);
        return bundle;
    }

    public BundleDTO toDTO(Bundle bundle) {
        if (bundle == null) return null;
        List<BucketDTO>bucketList =new ArrayList<>();
        for(Bucket b: bundle.getBuckets())
        {
            bucketList.add(bucketMapper.toDTO(b));
        }
        return new BundleDTO(
                bundle.getId(),
                bundle.getCode(),
                bucketList,
                bundle.getPeriod(),
                bundle.getCreatedAt()
        );
    }

}

