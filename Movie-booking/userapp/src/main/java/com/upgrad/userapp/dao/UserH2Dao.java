package com.upgrad.userapp.dao;

import com.upgrad.userapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserH2Dao extends JpaRepository<User, Integer> {

    Optional<User> findByUserId(int id);

}
