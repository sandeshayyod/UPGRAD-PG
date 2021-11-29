package com.upgrad.cc.bookmanagement.service;

import com.upgrad.cc.bookmanagement.dao.BookRepository;
import com.upgrad.cc.bookmanagement.dto.BookDto;
import com.upgrad.cc.bookmanagement.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class BookService {

    @Autowired
    BookRepository repository;

    @Autowired
    ModelMapper mapper;

    public BookDto requestBook(int bookId) {
        Optional<Book> bookOptional = repository.findById(bookId);
        if (bookOptional.isPresent() && bookOptional.get().getBookCount() > 0) {
            Book requestedBook = bookOptional.get();
            log.info("{} is available", requestedBook);
            requestedBook.setBookCount(requestedBook.getBookCount() - 1);
            requestedBook = repository.save(requestedBook);

            return mapper.map(requestedBook, BookDto.class);
        }
        return null;
    }

    public String returnBook(int bookId) {
        Optional<Book> bookOptional = repository.findById(bookId);
        if (bookOptional.isPresent() && bookOptional.get().getBookCount() > 0) {
            Book requestedBook = bookOptional.get();
            log.info("{} is available", requestedBook);
            requestedBook.setBookCount(requestedBook.getBookCount() - 1);
            repository.save(requestedBook);

            return "Book returned successfully";
        }
        return "Book return failed. Please check with reception";
    }
}
