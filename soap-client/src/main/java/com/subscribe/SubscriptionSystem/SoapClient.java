package com.subscribe.SubscriptionSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories;

@SpringBootApplication

@EnableAerospikeRepositories(basePackages = "com.subscribe.common.repository")
public class SoapClient {
	public static void main(String[] args) {
		SpringApplication.run(SubscriptionSystemApplication.class, args);
	}

}
