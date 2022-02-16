package com.upgrad.user.service;

import com.upgrad.user.dao.UserDao;
import com.upgrad.user.entities.User;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao ;

  @Override
  public User createUser(User user) {
    return userDao.save(user);
  }

  @Override
  public User getUserBasedOnId(int id) {

    Optional<User> user = userDao.findById(id);
    if(user.isPresent())
      return user.get();
    return null;
  }

  @Override
  public User updateUser(User user) {
    User storedUser = getUserBasedOnId(user.getUserId());

    // Copy the content of user into storedUser object

    storedUser.setDateOfBirth(user.getDateOfBirth());
    storedUser.setFirstName(user.getFirstName());
    storedUser.setDateOfBirth(user.getDateOfBirth());
    storedUser.setLastName(user.getLastName());
    storedUser.setPassword(user.getPassword());
    storedUser.setPhoneNumbers(user.getPhoneNumbers());
    storedUser.setUserId(user.getUserId());
    storedUser.setUserName(user.getUserName());

    return userDao.save(storedUser);
  }

  @Override
  public User deleteUser(User user) {

    userDao.delete(user);

    return  null ;
  }

  @Override
  public List<User> getAllUsers() {
    return userDao.findAll();
  }

  public UserDao getUserDao() {
    return userDao;
  }

  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }
}
