package com.subscribe.SubscriptionSystem.controller;

import com.subscribe.SubscriptionSystem.DTOs.OperatorDTO;
import com.subscribe.SubscriptionSystem.services.OperatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operators")
@RequiredArgsConstructor
public class OperatorController {

    @Autowired
    private  OperatorService operatorService;


    @PostMapping
    public ResponseEntity<OperatorDTO> create(@Valid @RequestBody OperatorDTO dto) {
        return ResponseEntity.ok(operatorService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<OperatorDTO>> getAll() {
        return ResponseEntity.ok(operatorService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperatorDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(operatorService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OperatorDTO> update(@PathVariable String id, @Valid @RequestBody OperatorDTO dto) {
        return ResponseEntity.ok(operatorService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        operatorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

