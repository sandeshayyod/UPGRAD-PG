package com.example.springkafkaproducerdemo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerService {

    @KafkaListener(topics = "${consumer.topic}", groupId = "${group.id}", containerFactory = "concurrentKafkaListenerContainerFactory")
    public void listen(@Payload List<User> users){
        System.out.println(users.get(0).getUserName()+ " "+users.get(0).getCreateDate());
    }
}
