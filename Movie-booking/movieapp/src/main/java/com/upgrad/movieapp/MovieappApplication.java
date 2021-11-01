package com.upgrad.movieapp;

import com.upgrad.movieapp.controller.MovieController;
import com.upgrad.movieapp.dto.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class MovieappApplication implements CommandLineRunner {

    @Autowired
    MovieController controller;

    public static void main(String[] args) {
        SpringApplication.run(MovieappApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        MovieDto movie = new MovieDto();
        movie.setMovieId(1);
        movie.setMovieName("Avatar");
        movie.setMovieDescription("english movie");
        movie.setReleaseDate(LocalDateTime.of(2009, 11, 11, 00, 00));
        movie.setDuration(180);
        movie.setCoverPhotoUrl("avatar cover photo url");
        movie.setTrailerUrl("avatar trailer url");

        MovieDto movie2 = new MovieDto();
        movie2.setMovieId(1);
        movie2.setMovieName("kgf");
        movie2.setMovieDescription("kannada movie");
        movie2.setReleaseDate(LocalDateTime.of(2021, 11, 11, 00, 00));
        movie2.setDuration(180);
        movie2.setCoverPhotoUrl("kgf cover photo url");
        movie2.setTrailerUrl("kgf trailer url");

        List<MovieDto> movies = Stream.of(movie, movie2).collect(Collectors.toList());
        controller.createAllMovie(movies);
    }
}
