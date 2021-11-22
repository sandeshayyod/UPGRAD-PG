package com.upgrad.bookmyshow.userapp.dao;

import com.upgrad.bookmyshow.userapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
}
