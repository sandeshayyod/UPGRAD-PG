package com.upgrad.bookmyshow.movieapp.controller;

import com.upgrad.bookmyshow.movieapp.dto.MovieDto;
import com.upgrad.bookmyshow.movieapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/movieapp/v1")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = {"/hello/{name}", "/hello", "/"})
    public String sayHello(@PathVariable(name = "name", required = false) String name) {
        String welcome = "Welcome" + (name == null ? "" : " " + name) + "!";
        return "Hi! the MovieApp is live and able to respond. " + welcome;
    }

    @PostMapping(value = "/movies", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDto> saveMovie(@RequestBody MovieDto movieDto) {
        movieDto = movieService.saveMovie(movieDto);
        return new ResponseEntity<>(movieDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/movies/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDto> getMovie(@PathVariable(name = "id") String movieId) {
        MovieDto movieDto = movieService.getMovieById(Long.parseLong(movieId));
        return new ResponseEntity<>(movieDto, HttpStatus.OK);
    }

    @PutMapping(value = "/movies", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDto> updateMovie(@RequestBody MovieDto movieDto) {
        movieDto = movieService.updateMovie(movieDto);
        return new ResponseEntity<>(movieDto, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/movies/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDto> deleteMovie(@PathVariable(name = "id") String movieId) {
        MovieDto movieDto = movieService.deleteMovieById(Long.parseLong(movieId));
        return new ResponseEntity<>(movieDto, HttpStatus.OK);
    }
}
