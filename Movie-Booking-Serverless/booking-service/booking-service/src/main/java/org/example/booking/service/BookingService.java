package org.example.booking.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.booking.dto.BookingDto;
import org.example.booking.entity.Booking;
import org.example.booking.repository.BookingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private BookingRepository bookingRepo;

    public BookingDto createBooking(BookingDto bookingDto) {
        Booking booking = mapper.map(bookingDto, Booking.class);
        Booking savedBooking = bookingRepo.save(booking);
        BookingDto savedBookingDto = mapper.map(savedBooking, BookingDto.class);
        sendToSQS(savedBookingDto);
        return savedBookingDto;
    }

    private void sendToSQS(BookingDto bookingDto) {
        String queueUrl = System.getenv("BOOKING_SQS_URL");
        ObjectMapper objectMapper = new ObjectMapper();
        String bookJSON = "";

        try {
            bookJSON = objectMapper.writeValueAsString(bookingDto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        AmazonSQS amazonSQS = AmazonSQSClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(bookJSON);
        amazonSQS.sendMessage(sendMessageRequest);
    }
}
