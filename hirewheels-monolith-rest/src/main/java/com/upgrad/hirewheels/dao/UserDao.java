package com.upgrad.hirewheels.dao;

import com.upgrad.hirewheels.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer> {

    List<User> getAllByFirstName(String firstName);

    List<User> getAllByFirstNameOrLastName(String firstName, String lastName);

    Optional<User> getUsersByEmail(String email);

    Optional<User> getUsersByMobileNo(String mobileNo);
}
