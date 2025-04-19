package com.subscribe.SubscriptionSystem.mappers;
import com.subscribe.SubscriptionSystem.DTOs.UserDTO;
import com.subscribe.SubscriptionSystem.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class UserMapper {
    public User toEntity(UserDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId() != null ? dto.getId() : UUID.randomUUID().toString());
        user.setName(dto.getName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setEmail(dto.getEmail());
        user.setWalletBalance(dto.getWalletBalance() != null ? dto.getWalletBalance() : 0.0);
        return user;
    }

    public UserDTO toDTO(User user) {
        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setEmail(user.getEmail());
        dto.setWalletBalance(user.getWalletBalance());
        return dto;
    }
}