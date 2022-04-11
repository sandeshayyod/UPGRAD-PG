package com.upgrad.doctorservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRating {
    @Id
    private String doctorId;
    private int rating;
}
