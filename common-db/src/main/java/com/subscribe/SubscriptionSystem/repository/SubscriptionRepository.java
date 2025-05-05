package com.subscribe.SubscriptionSystem.repository;

import com.subscribe.SubscriptionSystem.model.Subscription;
import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends AerospikeRepository<Subscription, String> {}
