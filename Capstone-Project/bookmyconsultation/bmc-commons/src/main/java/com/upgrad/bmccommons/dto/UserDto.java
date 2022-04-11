package com.upgrad.bmccommons.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
    private String id;
    private String firstName;
    private String lastName;
    private String dob;
    private String emailId;
    private String mobile;
    private LocalDate createdDate;
}
