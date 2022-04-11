package com.upgrad.commons.model.response;

import com.upgrad.commons.model.Error;
import com.upgrad.commons.model.Status;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationResponse {

    private final String notificationId;
    private final Status notificationStatus;
    private final Error error;
}
