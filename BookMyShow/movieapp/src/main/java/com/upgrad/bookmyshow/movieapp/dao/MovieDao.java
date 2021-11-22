package com.upgrad.bookmyshow.movieapp.dao;

import com.upgrad.bookmyshow.movieapp.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDao extends JpaRepository<Movie, Long> {
}
