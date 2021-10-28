package com.upgrad.movieapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MovieDto {

    private int movieId;

    private String movieName;

    private String movieDescription;

    private LocalDateTime releaseDate;

    private int duration;

    private String coverPhotoUrl;

    private String trailerUrl;

}
