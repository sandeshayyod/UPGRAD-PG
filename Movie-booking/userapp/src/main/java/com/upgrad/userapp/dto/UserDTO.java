package com.upgrad.userapp.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
public class UserDTO {

    private int userId;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private LocalDateTime dateOfBirth;

    private Set<Integer> phoneNumbers;

    public static void main(String[] args) {
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
        System.out.println(userDTO);

    }
}
