package com.upgrad.notificationservice.service;

import com.upgrad.notificationservice.model.NotificationException;

public interface NotificationService {

    String sendNotification(String recipient, String subject, String body)
            throws NotificationException;
}
