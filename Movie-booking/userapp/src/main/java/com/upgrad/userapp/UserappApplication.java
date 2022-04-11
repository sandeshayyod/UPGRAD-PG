package com.upgrad.userapp;

import com.upgrad.userapp.controller.UserController;
import com.upgrad.userapp.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class UserappApplication implements CommandLineRunner {

    @Autowired
    private UserController userController;

    public static void main(String[] args) {
        SpringApplication.run(UserappApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1);
        userDTO.setUsername("sandesh_ayyod");
        userDTO.setFirstName("sandesh");
        userDTO.setLastName("sandesh");
        userDTO.setPassword("123");
        userDTO.setDateOfBirth(LocalDateTime.of(1989, 07, 01, 00, 00));
        Set<Integer> phnos = new HashSet<>();
        phnos.add(810);
        phnos.add(914);
        userDTO.setPhoneNumbers(phnos);
        userController.createUser(userDTO);
    }
}
