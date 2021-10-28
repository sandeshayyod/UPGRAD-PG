package com.example.resttemplate.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IdeaDto implements Serializable {
    private int id;
    private String ideaTitle;
    private String ideaDesc;
    private String author;
}
