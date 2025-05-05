package com.subscribe.SubscriptionSystem.repository;

import com.subscribe.SubscriptionSystem.model.User;
import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends AerospikeRepository<User, String> {}