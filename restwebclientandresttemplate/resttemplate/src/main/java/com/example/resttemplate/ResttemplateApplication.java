package com.example.resttemplate;

import com.example.resttemplate.resttemplates.RestTemplateDemo;
import com.example.resttemplate.webclients.WebClientDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ResttemplateApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ResttemplateApplication.class, args);
        RestTemplateDemo demo = context.getBean(RestTemplateDemo.class);
        demo.fetchData();

        WebClientDemo wcdemo = context.getBean(WebClientDemo.class);
        wcdemo.fetchAllData();
    }

}
