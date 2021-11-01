package com.upgrad.movieapp.controller;

import com.upgrad.movieapp.dto.MovieDto;
import com.upgrad.movieapp.entity.Movie;
import com.upgrad.movieapp.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/movie-app/v1")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/movies", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto movieDto) {
        Movie movie = modelMapper.map(movieDto, Movie.class);
        Movie savedMovie = movieService.acceptMovieDetails(movie);
        MovieDto savedMovieDto = modelMapper.map(savedMovie, MovieDto.class);
        return new ResponseEntity<>(savedMovieDto, HttpStatus.CREATED);
    }

    @PostMapping(value = "/movies/all", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MovieDto>> createAllMovie(@RequestBody List<MovieDto> movieDtos) {
        List<Movie> movies = movieDtos.stream()
                .map(movieDto -> modelMapper.map(movieDto, Movie.class))
                .collect(Collectors.toList());
        List<Movie> savedMovies = movieService.acceptMovieDetails(movies);
        List<MovieDto> savedMovieDtos = savedMovies.stream()
                .map(movie -> modelMapper.map(movie, MovieDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(savedMovieDtos, HttpStatus.CREATED);
    }

    @GetMapping(value = "/movies/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDto> getMovieBasedOnId(@PathVariable(name = "id") int id) {
        Movie movie = movieService.getMovieDetails(id);
        MovieDto movieDto = modelMapper.map(movie, MovieDto.class);
        return new ResponseEntity<>(movieDto, HttpStatus.OK);
    }

    @GetMapping(value = "/movies", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovieDetails();
        List<MovieDto> movieDtos = movies.stream()
                .map(movie -> modelMapper.map(movie, MovieDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(movieDtos, HttpStatus.OK);
    }

    @PutMapping(value = "/movies/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDto> updateMovie(@PathVariable(name = "id") int id, @RequestBody MovieDto movieDto) {
        Movie movie = modelMapper.map(movieDto, Movie.class);
        Movie updatedMovie = movieService.updateMovieDetails(id, movie);
        MovieDto updatedMovieDto = modelMapper.map(updatedMovie, MovieDto.class);
        return new ResponseEntity<>(updatedMovieDto, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/movies/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDto> deleteMovie(@PathVariable(name = "id") int id) {
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
