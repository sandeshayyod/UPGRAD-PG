package com.upgrad.movieapp.cfg;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieAppCfg {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
