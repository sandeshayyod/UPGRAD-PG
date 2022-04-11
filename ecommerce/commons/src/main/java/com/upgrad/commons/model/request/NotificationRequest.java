package com.upgrad.commons.model.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationRequest {

    private final String recipient;
    private final String subject;
    private final String body;
}
