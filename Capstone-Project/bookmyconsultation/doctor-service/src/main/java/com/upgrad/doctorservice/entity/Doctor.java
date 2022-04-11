package com.upgrad.doctorservice.entity;

import com.upgrad.bmccommons.dto.Speciality;
import com.upgrad.bmccommons.dto.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;

@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String mobile;
    private String dob;
    private String emailId;
    private String pan;
    private Speciality speciality;
    private LocalDate registrationDate;
    private Status status;
    private String approvedBy;
    private String approverComments;
    private LocalDate verificationDate;
}
