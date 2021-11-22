package com.upgrad.bookmyshow.theatreapp.exception;

public class TheatreNotFoundException extends RuntimeException {
    public TheatreNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
