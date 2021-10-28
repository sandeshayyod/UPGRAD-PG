package com.upgrad.movieapp.dao;

import com.upgrad.movieapp.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This layer will be used to interact with Database
 */
public interface MovieDao extends JpaRepository<Movie, Integer> {

}
