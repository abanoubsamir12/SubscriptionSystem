package com.subscribe.SubscriptionSystem.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.*;
import org.springframework.beans.factory.annotation.Value;
@Configuration
public class KafkaConfig {

    @Bean
    public ConsumerFactory<String, SubscriptionMessage> consumerFactory(
            @Value("${spring.kafka.bootstrap-servers}") String bootstrapServers) {

        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "subscription-group");
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        return new DefaultKafkaConsumerFactory<>(
                config,
                new StringDeserializer(),
                new JsonDeserializer<>(SubscriptionMessage.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SubscriptionMessage> subscriptionListenerFactory(
            @Value("${spring.kafka.bootstrap-servers}") String bootstrapServers) {

        ConcurrentKafkaListenerContainerFactory<String, SubscriptionMessage> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory(bootstrapServers));
        factory.setConcurrency(10);

        return factory;
    }

}

