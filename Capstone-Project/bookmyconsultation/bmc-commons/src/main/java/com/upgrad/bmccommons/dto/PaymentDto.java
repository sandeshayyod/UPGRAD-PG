package com.upgrad.bmccommons.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaymentDto {
    private String paymentId;
    private String appointmentId;
    private LocalDateTime createDate;
}
