package com.upgrad.appointmentservice.config;

import com.upgrad.bmccommons.dto.AppointmentDto;
import com.upgrad.bmccommons.dto.PaymentDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${bootstrap.servers}")
    private String bootstrapServers;

    @Value("${group.id}")
    private String groupId;

    private Map<String, Object> getConsumerConfig() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        return configProps;
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, PaymentDto> paymentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PaymentDto> factory = new ConcurrentKafkaListenerContainerFactory<String, PaymentDto>();
        JsonDeserializer<PaymentDto> deserializer = new JsonDeserializer<>();
        deserializer.addTrustedPackages("*");
        ConsumerFactory<String, PaymentDto> consumerFactory = new DefaultKafkaConsumerFactory<>(getConsumerConfig(), new StringDeserializer(), deserializer);
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    ProducerFactory<String, AppointmentDto> producerFactory() {
        return new DefaultKafkaProducerFactory<>(getProducerConfigs());
    }

    private Map<String, Object> getProducerConfigs() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return configProps;
    }

    @Bean
    public KafkaTemplate<String, AppointmentDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
