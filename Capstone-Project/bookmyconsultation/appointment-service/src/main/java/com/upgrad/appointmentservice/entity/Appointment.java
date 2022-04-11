package com.upgrad.appointmentservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Appointment {
    @Id
    private String appointmentId;
    private String doctorId;
    private String doctorName;
    private String userId;
    private String userName;
    private String userEmailId;
    private LocalDate appointmentDate;
    private String timeSlot;
    private AppointmentStatus paymentStatus;
    private LocalDateTime createdDate;
    private String priorMedicalHistory;
    private String symptoms;
}
