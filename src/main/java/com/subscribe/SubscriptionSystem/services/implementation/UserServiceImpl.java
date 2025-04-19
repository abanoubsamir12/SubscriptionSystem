package com.subscribe.SubscriptionSystem.services.implementation;

import com.subscribe.SubscriptionSystem.DTOs.UserDTO;
import com.subscribe.SubscriptionSystem.mappers.UserMapper;
import com.subscribe.SubscriptionSystem.model.User;
import com.subscribe.SubscriptionSystem.repository.UserRepository;
import com.subscribe.SubscriptionSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;


    @Override
    public UserDTO create(UserDTO dto) {
        User user = userMapper.toEntity(dto);
        return userMapper.toDTO(userRepository.save(user));
    }
    @Override
    public UserDTO getById(String id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @Override
    public List<UserDTO> getAll() {
        List<UserDTO> result= new ArrayList<>();
        userRepository.findAll().forEach(user -> {result.add(userMapper.toDTO(user));});
        return result;
    }
    @Override
    public UserDTO update(String id, UserDTO dto) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        existing.setName(dto.getName());
        existing.setPhoneNumber(dto.getPhoneNumber());
        existing.setEmail(dto.getEmail());
        existing.setWalletBalance(dto.getWalletBalance());
        return userMapper.toDTO(userRepository.save(existing));
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }
}
