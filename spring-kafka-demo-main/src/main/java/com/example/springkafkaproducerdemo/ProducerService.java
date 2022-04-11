package com.example.springkafkaproducerdemo;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class ProducerService {
    @Value("${producer.topic}")
    private String producerTopic;
    private final KafkaTemplate<String,User> kafkaTemplate;
    public void publishUser(User user){

        log.info("Publishing "+user);
        kafkaTemplate.send(producerTopic,user);
    }
}
