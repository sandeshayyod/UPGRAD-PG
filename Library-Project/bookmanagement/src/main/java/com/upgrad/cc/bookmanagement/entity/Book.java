package com.upgrad.cc.bookmanagement.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookId;

    @Column(length = 50, nullable = false, unique = true)
    private String bookName;

    @Column(length = 500, nullable = true, unique = true)
    private String bookDescription;

    private int bookCount;
}
