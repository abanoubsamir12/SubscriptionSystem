package com.subscribe.SubscriptionSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SubscriptionSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(SubscriptionSystemApplication.class, args);
	}

}
