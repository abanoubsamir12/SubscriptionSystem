package com.subscribe.SubscriptionSystem.services;

import com.subscribe.SubscriptionSystem.DTOs.UserDTO;
import java.util.List;
public interface UserService {
    public UserDTO create(UserDTO user);
    public UserDTO getById(String id);
    public List<UserDTO> getAll();
    public UserDTO update(String id,  UserDTO dto);
    public void delete(String id);
}
