package com.subscribe.SubscriptionSystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
		"aerospike.host=localhost",
		"aerospike.port=3000",
		"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.data.aerospike.AerospikeDataAutoConfiguration"
})
class SubscriptionSystemApplicationTests {

	@Test
	void contextLoads() {
	}

}
