package com.upgrad.userapp.dao;

import com.upgrad.userapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UserMongoDao extends MongoRepository<User, Integer> {

    Optional<User> findByUserId(int id);

}
