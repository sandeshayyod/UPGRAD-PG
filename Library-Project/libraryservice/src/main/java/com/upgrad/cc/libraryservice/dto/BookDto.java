package com.upgrad.cc.libraryservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDto {
    private int bookId;
    private String bookName;
    private String bookDescription;
}
