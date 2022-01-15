package org.example.movie.controller;

import org.example.movie.dto.MovieDto;
import org.example.movie.entities.Movie;
import org.example.movie.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ModelMapper mapper;

    @PostMapping(path = "/movie", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto movieDto) {
        Movie movie = mapper.map(movieDto, Movie.class);
        Movie savedMovie = movieRepository.save(movie);
        MovieDto savedMovieDto = mapper.map(savedMovie, MovieDto.class);
        return new ResponseEntity<>(savedMovieDto, HttpStatus.CREATED);
    }

    @GetMapping(path = "/movie/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDto> getMovieById(@PathVariable String id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if(movieOptional.isPresent()) {
            MovieDto movieDTO = mapper.map(movieOptional.get(), MovieDto.class);
            return new ResponseEntity<>(movieDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
