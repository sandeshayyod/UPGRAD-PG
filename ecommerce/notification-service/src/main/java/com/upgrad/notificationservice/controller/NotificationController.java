package com.upgrad.notificationservice.controller;

import com.upgrad.commons.model.Error;
import com.upgrad.commons.model.Status;
import com.upgrad.commons.model.request.NotificationRequest;
import com.upgrad.commons.model.response.NotificationResponse;
import com.upgrad.notificationservice.model.NotificationException;
import com.upgrad.notificationservice.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/notification")
public final class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/send-notification")
    public NotificationResponse sendNotification(
            @RequestBody final NotificationRequest notificationRequest) {
        try {
            log.info("sending notification for recipient={} with status={}",
                    notificationRequest.getRecipient(),
                    Status.SUCCESSFUL);
            return NotificationResponse.builder()
                    .notificationId(notificationService.sendNotification(
                            notificationRequest.getRecipient(),
                            notificationRequest.getSubject(),
                            notificationRequest.getBody()))
                    .notificationStatus(Status.SUCCESSFUL)
                    .build();
        } catch (final NotificationException e) {
            log.info("exception occurred. sending notification for recipient={} with status={}",
                    notificationRequest.getRecipient(),
                    Status.FAILED, e);
            return NotificationResponse.builder()
                    .notificationStatus(Status.FAILED)
                    .error(Error.builder().message(e.getMessage()).build())
                    .build();
        }
    }
}
