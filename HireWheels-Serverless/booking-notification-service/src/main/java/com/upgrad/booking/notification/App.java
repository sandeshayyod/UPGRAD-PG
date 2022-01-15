package com.upgrad.booking.notification;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upgrad.booking.notification.dto.BookingDto;
import com.upgrad.booking.notification.dto.UserDTO;
import com.upgrad.booking.notification.dto.VehicleDTO;
import com.upgrad.booking.notification.service.UserService;
import com.upgrad.booking.notification.service.VehicleService;

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

            VehicleService vehicleService = new VehicleService();
            UserService userService = new UserService();

            UserDTO userDTO = userService.getUserById(bookingDto.getUserId());
            VehicleDTO vehicleDTO = vehicleService.getVehicleById(bookingDto.getVehicleId());

            String notification = "Thank you " + userDTO.getUsername() + "! Your booking for movie " + vehicleDTO.getVehicleModel() + " has been confirmed!";

            System.out.println(notification);

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
