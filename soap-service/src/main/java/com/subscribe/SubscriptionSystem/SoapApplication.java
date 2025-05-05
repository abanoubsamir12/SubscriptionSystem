package com.subscribe.SubscriptionSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication

@EnableAerospikeRepositories(basePackages = "com.subscribe.common.repository")
public class SoapApplication {
	public static void main(String[] args) {

		SpringApplication.run(SubscriptionSystemApplication.class, args);
	}

}
