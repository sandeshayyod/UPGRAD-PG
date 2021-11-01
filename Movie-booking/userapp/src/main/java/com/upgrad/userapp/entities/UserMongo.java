package com.upgrad.userapp.entities;

import lombok.*;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserMongo {

    @Id
    private int userId;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private LocalDateTime dateOfBirth;

    private Set<Integer> phoneNumbers;
}