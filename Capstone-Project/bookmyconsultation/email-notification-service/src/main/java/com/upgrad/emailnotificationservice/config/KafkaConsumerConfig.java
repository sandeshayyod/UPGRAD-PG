package com.upgrad.emailnotificationservice.config;

import com.upgrad.bmccommons.dto.AppointmentDto;
import com.upgrad.bmccommons.dto.DoctorDto;
import com.upgrad.bmccommons.dto.UserDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

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
    ConcurrentKafkaListenerContainerFactory<String, DoctorDto> doctorKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, DoctorDto> factory = new ConcurrentKafkaListenerContainerFactory<String, DoctorDto>();
        JsonDeserializer<DoctorDto> deserializer = new JsonDeserializer<>();
        deserializer.addTrustedPackages("*");
        ConsumerFactory<String, DoctorDto> consumerFactory = new DefaultKafkaConsumerFactory<>(getConsumerConfig(), new StringDeserializer(), deserializer);
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, UserDto> userKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserDto> factory = new ConcurrentKafkaListenerContainerFactory<String, UserDto>();
        JsonDeserializer<UserDto> deserializer = new JsonDeserializer<>();
        deserializer.addTrustedPackages("*");
        ConsumerFactory<String, UserDto> consumerFactory = new DefaultKafkaConsumerFactory<>(getConsumerConfig(), new StringDeserializer(), deserializer);
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, AppointmentDto> appointmentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, AppointmentDto> factory = new ConcurrentKafkaListenerContainerFactory<String, AppointmentDto>();
        JsonDeserializer<AppointmentDto> deserializer = new JsonDeserializer<>();
        deserializer.addTrustedPackages("*");
        ConsumerFactory<String, AppointmentDto> consumerFactory = new DefaultKafkaConsumerFactory<>(getConsumerConfig(), new StringDeserializer(), deserializer);
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

}
