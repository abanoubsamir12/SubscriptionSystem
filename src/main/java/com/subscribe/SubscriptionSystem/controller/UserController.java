package com.subscribe.SubscriptionSystem.controller;

import com.subscribe.SubscriptionSystem.DTOs.UserDTO;
import com.subscribe.SubscriptionSystem.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private  UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable String id,@Valid @RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.update(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

