package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.dao.RoleDao;
import com.upgrad.hirewheels.dao.UserDao;
import com.upgrad.hirewheels.entities.User;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao userRoleDao;

    @Override
    public User createUser(@NonNull String firstName, @NonNull String lastName, @NonNull String password, @NonNull String email,
                           @NonNull String mobileNo, float walletMoney, int roleId) {
        User user = new User(firstName, lastName, password, email, mobileNo, walletMoney, userRoleDao.findByRoleId(roleId));
        return userDao.save(user);
    }

    @Override
    public User getUser(@NonNull String email, @NonNull String password) {
        User user = userDao.getUsersByEmail(email).orElse(null);
        if (user == null) {
            logger.warn("User not Registered");
            return null;
        }
        if (!user.getPassword().equals(password)) {
            logger.warn("Unauthorized User");
            return null;
        }
        return user;
    }
}
