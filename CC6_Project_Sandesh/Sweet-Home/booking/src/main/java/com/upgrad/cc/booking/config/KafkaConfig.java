package com.upgrad.cc.booking.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Properties;

@Configuration
public class KafkaConfig {

    private final Environment env;

    public KafkaConfig(@Autowired Environment env) {
        this.env = env;
    }

    @Bean
    public Producer<String, String> producerClient() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", env.getProperty("kafka.producer.bootstrap.servers"));
        properties.put("acks", env.getProperty("kafka.producer.acks"));
        properties.put("retries", env.getProperty("kafka.producer.retries"));
        properties.put("linger.ms", env.getProperty("kafka.producer.linger.ms"));
        properties.put("partitioner.class", env.getProperty("kafka.producer.partitioner.class"));
        properties.put("key.serializer", env.getProperty("kafka.producer.key.serializer"));
        properties.put("value.serializer", env.getProperty("kafka.producer.value.serializer"));
        properties.put("request.timeout.ms", env.getProperty("kafka.producer.request.timeout.ms"));
        properties.put("timeout.ms", env.getProperty("kafka.producer.timeout.ms"));
        properties.put("max.in.flight.requests.per.connection", env.getProperty("kafka.producer.max.in.flight.requests.per.connection"));
        properties.put("retry.backoff.ms", env.getProperty("kafka.producer.retry.backoff.ms"));
        return new KafkaProducer<>(properties);
    }

}
