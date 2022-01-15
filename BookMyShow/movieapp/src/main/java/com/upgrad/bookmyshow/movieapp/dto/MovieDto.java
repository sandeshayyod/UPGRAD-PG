package com.upgrad.bookmyshow.movieapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private long movieId;
    private String movieName;
    private String movieDesc;
    private String genre;
    private String language;
    private Date releaseDate;
    private long duration;
}
