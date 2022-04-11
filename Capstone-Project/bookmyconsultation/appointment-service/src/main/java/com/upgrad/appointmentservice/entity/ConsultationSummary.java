package com.upgrad.appointmentservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConsultationSummary {
    @Id
    private String id;
    private String doctorId;
    private String userId;
    private String appointmentId;
    private List<Medicine> medicineList;
}
