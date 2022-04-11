package com.upgrad.appointmentservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Medicine {
    private String name;
    private String type;
    private String dosage;
    private String duration;
    private String frequency;
    private String remarks;
}
