package com.upgrad.appointmentservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;
import java.util.Map;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DoctorAvailability {
    @Id
    private String id;
    private String doctorId;
    private Map<String, List<String>> availabilityMap;
}
