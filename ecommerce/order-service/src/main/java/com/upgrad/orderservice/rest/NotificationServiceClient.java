package com.upgrad.orderservice.rest;

import com.upgrad.commons.model.request.NotificationRequest;
import com.upgrad.commons.model.response.NotificationResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public final class NotificationServiceClient {

    private static final String SERVICE_URL = "http://notification-service:8090/notification";

    private final RestTemplate restTemplate;

    public NotificationResponse sendNotification(final NotificationRequest notificationRequest) {
        return restTemplate
                .postForObject(SERVICE_URL + "/send-notification", notificationRequest,
                        NotificationResponse.class);
    }
}
