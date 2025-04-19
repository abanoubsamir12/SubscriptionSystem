package com.subscribe.SubscriptionSystem.mappers;

import com.subscribe.SubscriptionSystem.DTOs.SubscriptionDTO;
import com.subscribe.SubscriptionSystem.enums.SubscriptionStatus;
import com.subscribe.SubscriptionSystem.model.Subscription;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class SubscriptionMapper {
    public Subscription toEntity(SubscriptionDTO dto) {
        if (dto == null) return null;

        Subscription s = new Subscription();
        s.setId(dto.getId() != null ? dto.getId() : UUID.randomUUID().toString());
        s.setBundle_id(dto.getBundleId());
        s.setStartAt(Instant.now().toString());
        s.setOperator_id(dto.getOperatorId());
        s.setStatus(SubscriptionStatus.PENDING);
        s.setUser_id(dto.getUserId());
        return s;
    }

    public SubscriptionDTO toDTO(Subscription s) {
        if (s == null) return null;

        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setId(s.getId());
        dto.setStartAt(s.getStartAt());
        dto.setEndAt(s.getEndAt());
        dto.setOperatorId(s.getOperator_id());
        dto.setBundleId(s.getBundle_id());
        dto.setUserId(s.getUser_id());
        dto.setStatus(s.getStatus());
        return dto;
    }
}
