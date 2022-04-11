package com.upgrad.bmccommons.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DoctorAvailabilityDto {
    private String doctorId;
    private Map<String, List<String>> availabilityMap;
}
