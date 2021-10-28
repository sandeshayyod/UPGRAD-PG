package com.upgrad.hirewheels.cfg;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HireWheelsCfg {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
