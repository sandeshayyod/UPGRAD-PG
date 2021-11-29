package com.upgrad.cc.libraryservice.controller;

import com.upgrad.cc.libraryservice.dto.BookDto;
import com.upgrad.cc.libraryservice.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/library-app/v1")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping(value = "/book/request/{bookId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> requestBook(@PathVariable int bookId) {
        BookDto requestBook = libraryService.requestBook(bookId);
        return new ResponseEntity<>(requestBook, HttpStatus.OK);
    }

    @GetMapping(value = "/book/return/{bookId}")
    public ResponseEntity<String> returnBook(@PathVariable int bookId) {
        String response = libraryService.returnBook(bookId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
