package com.upgrad.cc.bookmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BookmanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookmanagementApplication.class, args);
    }

}
