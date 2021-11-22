package com.upgrad.bookmyshow.movieapp.service;

import com.upgrad.bookmyshow.movieapp.dto.MovieDto;

public interface MovieService {
    MovieDto getMovieById(long parseLong);

    MovieDto saveMovie(MovieDto movieDto);

    MovieDto updateMovie(MovieDto movieDto);

    MovieDto deleteMovieById(long parseLong);
}
