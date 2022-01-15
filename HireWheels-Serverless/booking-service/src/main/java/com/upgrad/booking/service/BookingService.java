package com.upgrad.booking.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upgrad.booking.dto.BookingDto;
import com.upgrad.booking.entity.Booking;
import com.upgrad.booking.repository.BookingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ModelMapper modelMapper;

    public BookingDto createBooking(BookingDto bookingDto) {
        Booking booking = modelMapper.map(bookingDto, Booking.class);
        Booking savedBooking = bookingRepository.save(booking);
        BookingDto savedBookingDto = modelMapper.map(savedBooking, BookingDto.class);
        sendToSQS(savedBookingDto);
        return savedBookingDto;
    }

    private void sendToSQS(BookingDto savedBookingDto) {
        String sqsUrl = System.getenv("HIREWHEEL_SQS_URL");
        AmazonSQS amazonSQS = AmazonSQSClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();
        String message = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            message = objectMapper.writeValueAsString(savedBookingDto);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        SendMessageRequest messageRequest = new SendMessageRequest()
                .withQueueUrl(sqsUrl)
                .withMessageBody(message);
        amazonSQS.sendMessage(messageRequest);

    }
}
