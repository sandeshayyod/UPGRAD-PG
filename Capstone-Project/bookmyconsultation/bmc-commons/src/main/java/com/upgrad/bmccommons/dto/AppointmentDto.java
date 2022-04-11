package com.upgrad.bmccommons.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AppointmentDto {
    private String appointmentId;
    private String doctorId;
    private String userId;
    private String userEmailId;
    private LocalDate appointmentDate;
    private String timeSlot;
}
