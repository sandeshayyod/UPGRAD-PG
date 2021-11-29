package com.upgrad.cc.libraryservice.service;

import com.upgrad.cc.libraryservice.dto.BookDto;
import com.upgrad.cc.libraryservice.feign.BookManagementClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class LibraryService {

    /**
     * using RestTemplate
     */
    @Value("${bookmanagement.url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    public BookDto requestBook(int bookId) {
        Map<String, Integer> uriMap = new HashMap<>();
        uriMap.put("bookId", bookId);
        return restTemplate.getForObject(url, BookDto.class, uriMap);
    }

    /**
     * using feign
     */
    @Autowired
    private BookManagementClient bookManagementClient;

    public String returnBook(int bookId) {
        log.info("requesting via feign");
        return bookManagementClient.returnBook(bookId);
    }


}
