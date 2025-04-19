package com.subscribe.SubscriptionSystem.services;

import com.subscribe.SubscriptionSystem.DTOs.BundleDTO;

import java.util.List;

public interface BundleService {
    public BundleDTO create(BundleDTO user);
    public BundleDTO getById(String id);
    public List<BundleDTO> getAll();
    public BundleDTO update(String id, BundleDTO dto);
    public void delete(String id);
}
