package com.upgrad.cc.booking.controller;

import com.upgrad.cc.booking.dto.BookingInfoDto;
import com.upgrad.cc.booking.dto.BookingTransactionDto;
import com.upgrad.cc.booking.entity.BookingInfoEntity;
import com.upgrad.cc.booking.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(value = "/booking")
public class BookingController {

    private BookingService bookingService;

    public BookingController(@Autowired BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingInfoEntity> doBooking(@RequestBody BookingInfoDto bookingInfoDto) {
        log.info("called doBooking with {}", bookingInfoDto);
        //invoke service class method to perform business logic
        BookingInfoEntity bookingInfoEntity = bookingService.doBooking(bookingInfoDto);
        log.info("completed doBooking with response {}", bookingInfoEntity);
        return new ResponseEntity<>(bookingInfoEntity, HttpStatus.CREATED);
    }

    @PostMapping(value = "/{bookingId}/transaction", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingInfoEntity> doBookingTransaction(@PathVariable int bookingId, @RequestBody BookingTransactionDto bookingTransactionDto) {
        log.info("called doBookingTransaction with bookingId={} and {}", bookingId, bookingTransactionDto);
        //invoke service class method to perform business logic
        BookingInfoEntity bookingInfoEntity = bookingService.doBookingTransaction(bookingId, bookingTransactionDto);
        log.info("completed doBookingTransaction with response {}", bookingInfoEntity);
        return new ResponseEntity<>(bookingInfoEntity, HttpStatus.CREATED);
    }


}
