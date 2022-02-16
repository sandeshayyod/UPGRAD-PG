package com.sweethome.bookingservice.controller;

import com.sweethome.bookingservice.exceptions.CustomException;
import com.sweethome.bookingservice.model.dto.CustomResponse;
import com.sweethome.bookingservice.model.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sweethome.bookingservice.model.dto.BookingDto;
import com.sweethome.bookingservice.model.entity.BookingInfoEntity;
import com.sweethome.bookingservice.service.BookingService;

@RestController
public class BookingController {
	
	@Autowired
	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}
	BookingService bookingService;


	@PostMapping("/booking/{bookingId}/transaction")
	public ResponseEntity<BookingInfoEntity> doPayment( @RequestBody PaymentDto paymentDetails) throws Exception {
		BookingInfoEntity bookingInfo =  bookingService.doPayment(paymentDetails);
		return new ResponseEntity<BookingInfoEntity>(bookingInfo, HttpStatus.CREATED);
	}

	@PostMapping("/booking")
	public ResponseEntity<BookingInfoEntity> bookingDetails(@RequestBody BookingDto bookingRequest) throws Exception {
		BookingInfoEntity bookingInfo =  this.bookingService.bookingDetails(bookingRequest);
		return new ResponseEntity<BookingInfoEntity>(bookingInfo, HttpStatus.CREATED);
	}






}
