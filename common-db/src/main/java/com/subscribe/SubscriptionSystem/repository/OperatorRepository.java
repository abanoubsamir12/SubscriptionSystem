package com.subscribe.SubscriptionSystem.repository;

import com.subscribe.SubscriptionSystem.model.Operator;
import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends AerospikeRepository<Operator, String> {}