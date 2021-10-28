package com.upgrad.movieapp.service;

import com.upgrad.movieapp.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {

    Movie acceptMovieDetails(Movie movie);

    List<Movie> acceptMovieDetails(List<Movie> movies);

    Movie getMovieDetails(int id);

    List<Movie> getAllMovieDetails();

    Movie updateMovieDetails(int id, Movie movie);

    boolean deleteMovie(int id);

    Page<Movie> getPaginatedMovie(Pageable pageable);
}
