package com.upgrad.bmccommons.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ConsultationSummaryDto {
    private String id;
    private String doctorId;
    private String userId;
    private String appointmentId;
    private List<MedicineDto> medicineList;
}
