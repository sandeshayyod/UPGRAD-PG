package com.upgrad.cc.bookmanagement.controller;

import com.upgrad.cc.bookmanagement.dto.BookDto;
import com.upgrad.cc.bookmanagement.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(value = "/book-app/v1")
public class BookController {

    @Autowired
    BookService bookService;

    /**
     * GET method to request for book based on bookId
     *
     * @param bookId
     * @return BookDto
     */
    @GetMapping(value = "/book/request/{bookId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> requestBook(@PathVariable int bookId) {
        BookDto requestedBook = bookService.requestBook(bookId);
        if (requestedBook == null) {
            log.warn("Request Book with BookId {} not found in library", bookId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        log.info("Request book with id {} is booked", bookId);
        return new ResponseEntity<>(requestedBook, HttpStatus.OK);
    }

    /**
     * GET method to return book based on bookId
     *
     * @param bookId
     * @return BookDto
     */
    @GetMapping(value = "/book/return/{bookId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> returnBook(@PathVariable int bookId) {
        String response = bookService.returnBook(bookId);
        log.info(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
