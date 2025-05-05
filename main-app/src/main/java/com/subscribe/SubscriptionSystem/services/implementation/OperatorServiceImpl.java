package com.subscribe.SubscriptionSystem.services.implementation;

import com.subscribe.SubscriptionSystem.DTOs.OperatorDTO;
import com.subscribe.SubscriptionSystem.mappers.OperatorMapper;
import com.subscribe.SubscriptionSystem.model.Operator;
import com.subscribe.SubscriptionSystem.repository.OperatorRepository;
import com.subscribe.SubscriptionSystem.services.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    private OperatorRepository operatorRepository;

    @Autowired
    private OperatorMapper operatorMapper;

    /**
     * Creates a new operator.
     * Converts the DTO to an entity, saves it to the database,
     * then returns the saved entity as a DTO.
     */
    public OperatorDTO create(OperatorDTO dto) {
        Operator entity = operatorMapper.toEntity(dto);
        return operatorMapper.toDTO(operatorRepository.save(entity));
    }

    /**
     * Retrieves all operators from the database and maps them to DTOs.
     */
    public List<OperatorDTO> getAll() {
        List<OperatorDTO> result = new ArrayList<>();
        operatorRepository.findAll().forEach(operator -> {
            result.add(operatorMapper.toDTO(operator));
        });
        return result;
    }

    /**
     * Retrieves a single operator by ID.
     * Throws a NoSuchElementException if the operator does not exist.
     */
    public OperatorDTO getById(String id) {
        return operatorRepository.findById(id)
                .map(operatorMapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException("Operator not found"));
    }

    /**
     * Updates an existing operator’s name and status using the provided DTO.
     * Returns the updated operator as a DTO.
     */
    public OperatorDTO update(String id, OperatorDTO dto) {
        Operator existing = operatorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Operator not found"));

        // Update fields
        existing.setName(dto.getName());
        existing.setStatus(dto.isStatus());

        return operatorMapper.toDTO(operatorRepository.save(existing));
    }

    /**
     * Deletes an operator from the database by ID.
     */
    public void delete(String id) {
        operatorRepository.deleteById(id);
    }
}
