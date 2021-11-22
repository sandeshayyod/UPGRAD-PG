package com.upgrad.bookmyshow.userapp.exception;

public class UserDataNotFoundException extends RuntimeException {
    public UserDataNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
