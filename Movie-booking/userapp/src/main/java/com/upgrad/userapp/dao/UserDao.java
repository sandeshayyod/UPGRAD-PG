package com.upgrad.userapp.dao;

import com.upgrad.userapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User, Integer> {

}
