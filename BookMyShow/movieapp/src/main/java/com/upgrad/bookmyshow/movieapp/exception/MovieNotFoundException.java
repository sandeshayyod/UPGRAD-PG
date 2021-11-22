package com.upgrad.bookmyshow.movieapp.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
