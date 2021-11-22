package com.upgrad.bookmyshow.movieapp.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Builder
@Data
public class MovieDto {
    private long movieId;
    private String movieName;
    private String movieDesc;
    private String genre;
    private String language;
    private Date releaseDate;
    private long duration;
}
