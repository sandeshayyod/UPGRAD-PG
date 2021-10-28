package com.upgrad.userapp.service;

import com.upgrad.userapp.entities.User;

import java.util.List;


public interface UserService {

    User createUser(User user);

    User getUserBasedOnId(int id);

    User updateUser(User user);

    User deleteUser(User user);

    List<User> getAllUsers();

}
