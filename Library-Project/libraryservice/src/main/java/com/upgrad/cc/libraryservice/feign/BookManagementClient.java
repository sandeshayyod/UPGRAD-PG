package com.upgrad.cc.libraryservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "book-service")
public interface BookManagementClient {

    @RequestMapping(value = "${bookmanagement.return.book.endpoint}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    String returnBook(@PathVariable int bookId);
}
