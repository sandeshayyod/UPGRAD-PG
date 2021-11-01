package com.upgrad.movieapp.dao;

import com.upgrad.movieapp.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * This layer will be used to interact with Database
 */
public interface MovieMongoDao extends MongoRepository<Movie, Integer> {

}
