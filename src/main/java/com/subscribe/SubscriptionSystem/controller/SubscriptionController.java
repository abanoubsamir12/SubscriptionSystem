package com.subscribe.SubscriptionSystem.controller;

import com.subscribe.SubscriptionSystem.DTOs.BulkSubscriptionRequest;
import com.subscribe.SubscriptionSystem.DTOs.SubscriptionDTO;
import com.subscribe.SubscriptionSystem.services.BulkSubscriptionService;
import com.subscribe.SubscriptionSystem.services.SubscriptionService;
import com.subscribe.SubscriptionSystem.soap.ws.ActivateBundleResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    @Autowired
    private SubscriptionService service;
    @Autowired
    private BulkSubscriptionService bulkService;

    @PostMapping
    public ResponseEntity<SubscriptionDTO> create(@Valid @RequestBody SubscriptionDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/bulkSubscription")
    public ResponseEntity<String> bulkSubscriptionService(@RequestBody BulkSubscriptionRequest request)
    {
        bulkService.bulkSubscribe(request.getUsersId(), request.getBundleId());
        return ResponseEntity.accepted().body("Bulk subscription started");
    }

    @PostMapping("/activateSubscription/{id}")
    public ResponseEntity<ActivateBundleResponse> activateSubscription(@PathVariable String id){
        return ResponseEntity.ok(service.tryActivateSubscription(id));
    }
}
