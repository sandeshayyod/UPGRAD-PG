package org.example.movie.repository;

import org.example.movie.entities.Movie;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface MovieRepository extends CrudRepository<Movie, String> {
}
