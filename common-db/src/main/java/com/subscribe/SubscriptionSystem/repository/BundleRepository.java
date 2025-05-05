package com.subscribe.SubscriptionSystem.repository;

import com.subscribe.SubscriptionSystem.model.Bundle;
import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BundleRepository extends AerospikeRepository<Bundle, String> {}
