package com.sessions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String bookName) {
        super(bookName);
    }
}
