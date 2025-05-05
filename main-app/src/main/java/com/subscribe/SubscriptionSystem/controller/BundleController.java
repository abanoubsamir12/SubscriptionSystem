package com.subscribe.SubscriptionSystem.controller;


import com.subscribe.SubscriptionSystem.DTOs.BundleDTO;
import com.subscribe.SubscriptionSystem.services.BundleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/bundles")
@RequiredArgsConstructor
public class BundleController {
    @Autowired
    private BundleService bundleService;

    @PostMapping
    public ResponseEntity<BundleDTO> create(@Valid @RequestBody BundleDTO dto) {
        return ResponseEntity.ok(bundleService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<BundleDTO>> getAll() {
        return ResponseEntity.ok(bundleService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BundleDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(bundleService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BundleDTO> update(@PathVariable String id, @Valid @RequestBody BundleDTO dto) {
        return ResponseEntity.ok(bundleService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        bundleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
