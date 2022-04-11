package com.upgrad.bmccommons.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DoctorDto {
    private String id;
    private String firstName;
    private String lastName;
    private String mobile;
    private String dob;
    private String emailId;
    private String pan;
    private Speciality speciality = Speciality.GENERAL_PHYSICIAN;
    private LocalDate registrationDate;
    private Status status = Status.PENDING;
    private String approvedBy;
    private String approverComments;
    private LocalDate verificationDate;
}
