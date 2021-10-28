package com.upgrad.movieapp.service;

import com.upgrad.movieapp.dao.MovieDao;
import com.upgrad.movieapp.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao movieDao;

    @Override
    public Movie acceptMovieDetails(Movie movie) {
        return movieDao.save(movie);
    }

    @Override
    public List<Movie> acceptMovieDetails(List<Movie> movies) {
        return movieDao.saveAll(movies);
    }

    @Override
    public Movie getMovieDetails(int id) {
        return movieDao.findById(id).orElse(null);
    }

    @Override
    public List<Movie> getAllMovieDetails() {
        return movieDao.findAll();
    }

    @Override
    public Movie updateMovieDetails(int id, Movie movie) {
        Optional<Movie> savedMovieOptional = movieDao.findById(id);
        if (savedMovieOptional.isPresent()) {
            Movie savedMovie = savedMovieOptional.get();
            savedMovie.setMovieName(movie.getMovieName());
            savedMovie.setMovieDescription(movie.getMovieDescription());
            savedMovie.setReleaseDate(movie.getReleaseDate());
            savedMovie.setDuration(movie.getDuration());
            savedMovie.setTrailerUrl(movie.getTrailerUrl());
            savedMovie.setCoverPhotoUrl(movie.getCoverPhotoUrl());
            return movieDao.save(savedMovie);
        }
        return movieDao.save(movie);
    }

    @Override
    public boolean deleteMovie(int id) {
        Optional<Movie> savedMovieOptional = movieDao.findById(id);
        if (savedMovieOptional.isPresent()) {
            movieDao.delete(savedMovieOptional.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Page<Movie> getPaginatedMovie(Pageable pageable) {
        return movieDao.findAll(pageable);
    }
}
