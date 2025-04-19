package com.subscribe.SubscriptionSystem.services;

import com.subscribe.SubscriptionSystem.DTOs.OperatorDTO;

import java.util.List;

public interface OperatorService {
    public OperatorDTO create(OperatorDTO user);
    public OperatorDTO getById(String id);
    public List<OperatorDTO> getAll();
    public OperatorDTO update(String id, OperatorDTO dto);
    public void delete(String id);
}
