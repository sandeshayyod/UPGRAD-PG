package com.upgrad.bmccommons.dto;

import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MedicineDto {
    private String name;
    private String type;
    private String dosage;
    private String duration;
    private String frequency;
    private String remarks;
}
