package com.upgrad.emailnotificationservice.controller;

import com.upgrad.emailnotificationservice.service.SesEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NotificationController {

    private final SesEmailService emailService;

    @PostMapping(value = "/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestParam(value = "emailId") String emailId) {
        emailService.sendEmail(emailId, "Welcome", "userwelcome.ftl");
        return ResponseEntity.ok("Email Sent");
    }

    @PostMapping(value = "/verifyEmail")
    public ResponseEntity<String> verifyEmail(@RequestParam(value = "emailId") String emailId) {
        emailService.verifyEmail(emailId);
        return ResponseEntity.ok("Verification Email Sent");
    }
}
