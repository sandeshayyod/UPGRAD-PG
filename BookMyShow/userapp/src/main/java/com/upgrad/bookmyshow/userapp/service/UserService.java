package com.upgrad.bookmyshow.userapp.service;

import com.upgrad.bookmyshow.userapp.dto.UserDto;

public interface UserService {

    UserDto getUser(long userId);

    UserDto saveUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    UserDto deleteUser(long userId);

}
