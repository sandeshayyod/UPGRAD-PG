package com.upgrad.appointmentservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppointmentServiceAppConfig {

    @Bean
    @Primary
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
