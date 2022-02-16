package com.upgrad.user.service;

import com.upgrad.user.entities.User;
import java.util.List;


public interface UserService {

  public User createUser(User user);

  public User getUserBasedOnId(int id);

  public User updateUser(User user);

  public User deleteUser(User user);

  public List<User>  getAllUsers();
}
