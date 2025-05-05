package com.subscribe.SubscriptionSystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest

@EnableAerospikeRepositories(basePackages = "com.subscribe.common.repository")
class SubscriptionSystemApplicationTests {

	@Test
	void contextLoads() {
	}

}
