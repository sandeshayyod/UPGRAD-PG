package com.upgrad.bmccommons.dto;

import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApproverDto {
    private String approvedBy;
    private String approverComments;
}
