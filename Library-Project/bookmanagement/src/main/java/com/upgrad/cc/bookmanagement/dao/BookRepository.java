package com.upgrad.cc.bookmanagement.dao;

import com.upgrad.cc.bookmanagement.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
