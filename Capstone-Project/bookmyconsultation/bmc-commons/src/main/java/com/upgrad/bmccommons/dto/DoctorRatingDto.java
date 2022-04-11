package com.upgrad.bmccommons.dto;

import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DoctorRatingDto {
    private String doctorId;
    private int rating;
}
