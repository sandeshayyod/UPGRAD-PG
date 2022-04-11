package com.upgrad.appointmentservice.controller;

import com.upgrad.appointmentservice.service.ConsultationService;
import com.upgrad.bmccommons.dto.ConsultationSummaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @PostMapping(path = "prescriptions")
    public ResponseEntity<String> sendPrescriptions(@RequestBody ConsultationSummaryDto consultationSummary) {
        consultationService.saveConsultationSummary(consultationSummary);
        return new ResponseEntity<>("Prescriptions Uploaded", HttpStatus.OK);
    }
}
