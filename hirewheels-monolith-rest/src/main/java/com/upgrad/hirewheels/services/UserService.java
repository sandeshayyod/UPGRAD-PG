package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.entities.User;

public interface UserService {

    User createUser(String firstName, String lastName, String password, String email, String mobileNo, float walletMoney, int roleId);

    User getUser(String email, String password);
}
