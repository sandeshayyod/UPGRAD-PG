package com.upgrad.userservice.dao;

import com.upgrad.userservice.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDao extends MongoRepository<User, String> {
}
