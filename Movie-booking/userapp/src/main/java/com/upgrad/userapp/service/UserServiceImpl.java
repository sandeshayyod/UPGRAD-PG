package com.upgrad.userapp.service;

import com.upgrad.userapp.dao.UserH2Dao;
import com.upgrad.userapp.dao.UserMongoDao;
import com.upgrad.userapp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMongoDao userDao;

    @Override
    public User createUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User getUserBasedOnId(int id) {
        return userDao.findById(id).get();
    }

    @Override
    public User updateUser(User user) {
        Optional<User> savedUserOptional = userDao.findByUserId(user.getUserId());
        if (savedUserOptional.isPresent()) {
            User savedUser = savedUserOptional.get();
            savedUser.setUsername(user.getUsername());
            savedUser.setDateOfBirth(user.getDateOfBirth());
            savedUser.setFirstName(user.getFirstName());
            savedUser.setLastName(user.getLastName());
            savedUser.setPassword(user.getPassword());
            savedUser.setPhoneNumbers(user.getPhoneNumbers());
            return userDao.save(savedUser);
        } else {
            return null;
        }
    }

    @Override
    public User deleteUser(User user) {
        Optional<User> savedUserOptional = userDao.findById(user.getUserId());
        if (savedUserOptional.isPresent()) {
            userDao.delete(savedUserOptional.get());
            return savedUserOptional.get();
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }
}
