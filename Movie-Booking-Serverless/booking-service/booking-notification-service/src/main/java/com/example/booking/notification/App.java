package com.example.booking.notification;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.example.booking.notification.dto.BookingDto;
import com.example.booking.notification.dto.MovieDto;
import com.example.booking.notification.dto.UserDto;
import com.example.booking.notification.service.MovieService;
import com.example.booking.notification.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App implements RequestHandler<SQSEvent, Void> {
    @Override
    public Void handleRequest(SQSEvent sqsEvent, Context context) {
        sqsEvent.getRecords()
                .forEach(sqsMessage -> processMessage(sqsMessage.getBody()));
        return null;
    }

    private void processMessage(String body) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            BookingDto bookingDto = objectMapper.readValue(body, BookingDto.class);

            UserService userService = new UserService();
            MovieService movieService = new MovieService();

            UserDto userDto = userService.getUserById(bookingDto.getUserId());
            MovieDto movieDto = movieService.getMovieById(bookingDto.getMovieId());

            String notification = "Thank you " + userDto.getUsername() + "! Your booking for movie " + movieDto.getMovieName() + " has been confirmed!";

            System.out.println(notification);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
