package com.example.restdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestDemoApplication {

    public static void main(String[] args) {
        System.out.println("Hello from Rest Demo");
        SpringApplication.run(RestDemoApplication.class, args);
    }

}
