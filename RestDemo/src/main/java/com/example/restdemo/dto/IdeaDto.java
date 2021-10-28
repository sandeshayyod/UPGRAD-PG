package com.example.restdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IdeaDto {
    private int id;
    private String ideaTitle;
    private String ideaDesc;
    private String author;
}
