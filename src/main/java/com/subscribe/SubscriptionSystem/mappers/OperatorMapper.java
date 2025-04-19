package com.subscribe.SubscriptionSystem.mappers;

import com.subscribe.SubscriptionSystem.DTOs.OperatorDTO;
import com.subscribe.SubscriptionSystem.model.Operator;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class OperatorMapper {
    public Operator toEntity(OperatorDTO dto) {
        if (dto == null) return null;

        Operator o = new Operator();
        o.setId(dto.getId() != null ? dto.getId() : UUID.randomUUID().toString());
        o.setName(dto.getName());
        o.setStatus(dto.isStatus());
        o.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt().toString() : Instant.now().toString());
        return o;
    }

    public OperatorDTO toDTO(Operator o) {
        if (o == null) return null;

        OperatorDTO dto = new OperatorDTO();
        dto.setId(o.getId());
        dto.setName(o.getName());
        dto.setStatus(o.isStatus());
        dto.setCreatedAt(o.getCreatedAt());
        return dto;
    }

}
