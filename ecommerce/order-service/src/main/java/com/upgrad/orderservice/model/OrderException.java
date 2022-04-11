package com.upgrad.orderservice.model;

public class OrderException extends Exception {

    public OrderException(final String message) {
        super(message);
    }

    public OrderException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
