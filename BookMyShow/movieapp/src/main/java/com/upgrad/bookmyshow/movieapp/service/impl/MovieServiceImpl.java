package com.upgrad.bookmyshow.movieapp.service.impl;

import com.upgrad.bookmyshow.movieapp.dao.MovieDao;
import com.upgrad.bookmyshow.movieapp.dto.MovieDto;
import com.upgrad.bookmyshow.movieapp.entity.Movie;
import com.upgrad.bookmyshow.movieapp.exception.MovieNotFoundException;
import com.upgrad.bookmyshow.movieapp.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieDao movieDao;
    private final ModelMapper modelMapper;

    @Autowired
    public MovieServiceImpl(MovieDao movieDao, ModelMapper modelMapper) {
        this.movieDao = movieDao;
        this.modelMapper = modelMapper;
    }


    @Override
    public MovieDto getMovieById(long movieId) {
        Optional<Movie> movieOptional = movieDao.findById(movieId);
        Movie movie = movieOptional.orElseThrow(
                () -> new MovieNotFoundException("Movie with id " + movieId + " not found in BookMyShow")
        );
        return modelMapper.map(movie, MovieDto.class);
    }

    @Override
    public MovieDto saveMovie(MovieDto movieDto) {
        Movie movie = modelMapper.map(movieDto, Movie.class);
        movie = movieDao.save(movie);
        return modelMapper.map(movie, MovieDto.class);
    }

    @Override
    public MovieDto updateMovie(MovieDto movieDto) {
        Optional<Movie> movieOptional = movieDao.findById(movieDto.getMovieId());
        Movie updatedMovie = movieOptional
                .map(movie -> {
                    movie.setMovieName(movieDto.getMovieName());
                    movie.setMovieDesc(movieDto.getMovieDesc());
                    movie.setDuration(movieDto.getDuration());
                    movie.setGenre(movieDto.getGenre());
                    movie.setLanguage(movieDto.getLanguage());
                    movie.setReleaseDate(movieDto.getReleaseDate());
                    return movie;
                })
                .orElseThrow(() -> new MovieNotFoundException("Movie with id " + movieDto.getMovieId() + " not found in BookMyShow")
                );
        updatedMovie = movieDao.save(updatedMovie);
        return modelMapper.map(updatedMovie, MovieDto.class);
    }

    @Override
    public MovieDto deleteMovieById(long movieId) {
        Optional<Movie> movieOptional = movieDao.findById(movieId);
        Movie deletedMovie = movieOptional
                .map(movie -> {
                    movieDao.delete(movie);
                    return movie;
                })
                .orElseThrow(() -> new MovieNotFoundException("Movie with id " + movieId + " not found in BookMyShow")
                );
        return modelMapper.map(deletedMovie, MovieDto.class);
    }
}
