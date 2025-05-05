package com.subscribe.SubscriptionSystem.services.implementation;

import com.subscribe.SubscriptionSystem.DTOs.UserDTO;
import com.subscribe.SubscriptionSystem.mappers.UserMapper;
import com.subscribe.SubscriptionSystem.model.User;
import com.subscribe.SubscriptionSystem.repository.UserRepository;
import com.subscribe.SubscriptionSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.List;

/**
 * Implementation of the UserService interface.
 * Handles business logic related to User operations.
 */
@Service
public class UserServiceImpl implements UserService {

    // Inject the UserRepository to interact with the database
    @Autowired
    private UserRepository userRepository;

    // Inject the UserMapper to convert between Entity and DTO
    @Autowired
    private UserMapper userMapper;

    /**
     * Creates a new user in the system.
     * @param dto UserDTO containing user data.
     * @return UserDTO of the saved user.
     */
    @Override
    public UserDTO create(UserDTO dto) {
        User user = userMapper.toEntity(dto); // Convert DTO to entity
        return userMapper.toDTO(userRepository.save(user)); // Save and return the DTO
    }

    /**
     * Retrieves a user by their ID.
     * @param id The ID of the user.
     * @return UserDTO if found.
     * @throws NoSuchElementException if user does not exist.
     */
    @Override
    public UserDTO getById(String id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO) // Convert entity to DTO if found
                .orElseThrow(() -> new NoSuchElementException("User not found")); // Handle not found
    }

    /**
     * Retrieves all users from the system.
     * @return List of UserDTOs.
     */
    @Override
    public List<UserDTO> getAll() {
        List<UserDTO> result = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            result.add(userMapper.toDTO(user)); // Convert each user entity to DTO and add to list
        });
        return result;
    }

    /**
     * Updates a user based on the given ID and data.
     * @param id ID of the user to update.
     * @param dto UserDTO containing new data.
     * @return Updated UserDTO.
     * @throws NoSuchElementException if user does not exist.
     */
    @Override
    public UserDTO update(String id, UserDTO dto) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        // Update fields
        existing.setName(dto.getName());
        existing.setPhoneNumber(dto.getPhoneNumber());
        existing.setEmail(dto.getEmail());
        existing.setWalletBalance(dto.getWalletBalance());

        // Save and return the updated user
        return userMapper.toDTO(userRepository.save(existing));
    }

    /**
     * Deletes a user by their ID.
     * @param id ID of the user to delete.
     */
    @Override
    public void delete(String id) {
        userRepository.deleteById(id); // Deletes the user by ID
    }
}
