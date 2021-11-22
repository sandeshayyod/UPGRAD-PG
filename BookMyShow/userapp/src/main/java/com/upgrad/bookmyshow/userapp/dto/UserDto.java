package com.upgrad.bookmyshow.userapp.dto;

import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String name;
    private String password;
    private String address;
    private String phoneNumber;
}
