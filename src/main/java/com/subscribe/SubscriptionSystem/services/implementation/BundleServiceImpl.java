package com.subscribe.SubscriptionSystem.services.implementation;

import com.subscribe.SubscriptionSystem.DTOs.BundleDTO;
import com.subscribe.SubscriptionSystem.mappers.BundleMapper;
import com.subscribe.SubscriptionSystem.model.Bundle;
import com.subscribe.SubscriptionSystem.repository.BundleRepository;
import com.subscribe.SubscriptionSystem.services.BundleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BundleServiceImpl implements BundleService {

    // Inject the repository to interact with the database
    @Autowired
    private BundleRepository bundleRepository;

    // Inject the mapper to convert between DTO and entity
    @Autowired
    private BundleMapper bundleMapper;

    /**
     * Creates a new bundle by converting the DTO to an entity, saving it,
     * and then returning the saved entity as a DTO.
     */
    @Override
    public BundleDTO create(BundleDTO dto) {
        Bundle entity = bundleMapper.toEntity(dto);
        return bundleMapper.toDTO(bundleRepository.save(entity));
    }

    /**
     * Retrieves all bundles from the database and maps them to DTOs.
     */
    public List<BundleDTO> getAll() {
        List<BundleDTO> result = new ArrayList<>();
        bundleRepository.findAll().forEach(bundle -> {
            result.add(bundleMapper.toDTO(bundle));
        });
        return result;
    }

    /**
     * Retrieves a bundle by its ID. If not found, throws an exception.
     */
    public BundleDTO getById(String id) {
        return bundleRepository.findById(id)
                .map(bundleMapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException("Bundle not found"));
    }

    /**
     * Updates an existing bundle. Only specific fields are updated (e.g., code and period).
     */
    @Override
    public BundleDTO update(String id, BundleDTO dto) {
        Bundle existing = bundleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bundle not found"));

        // Update mutable fields
        existing.setCode(dto.getCode());
        existing.setPeriod(dto.getPeriod());

        // Optional: setBuckets if needed
        // existing.setBuckets(...);

        return bundleMapper.toDTO(bundleRepository.save(existing));
    }

    /**
     * Deletes a bundle by its ID.
     */
    @Override
    public void delete(String id) {
        bundleRepository.deleteById(id);
    }
}
