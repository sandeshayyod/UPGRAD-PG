package com.upgrad.bookmyshow.userapp.service.impl;

import com.upgrad.bookmyshow.userapp.dao.UserDao;
import com.upgrad.bookmyshow.userapp.dto.UserDto;
import com.upgrad.bookmyshow.userapp.entity.User;
import com.upgrad.bookmyshow.userapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.UnmodifiableSetException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserDao userDao, ModelMapper modelMapper) {
        this.userDao = userDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto getUser(long userId) {
        Optional<User> userOptional = userDao.findById(userId);
        User user = userOptional.orElseThrow(() -> new UnmodifiableSetException("User with " + userId + " not found"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user = userDao.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        long userId = userDto.getId();
        Optional<User> userOptional = userDao.findById(userId);
        User updatedUser = userOptional
                .map(user -> {
                    user.setName(userDto.getName());
                    user.setAddress(userDto.getAddress());
                    user.setPassword(userDto.getPassword());
                    user.setPhoneNumber(userDto.getPhoneNumber());
                    return userDao.save(user);
                })
                .orElseThrow(() -> new UnmodifiableSetException("User with " + userId + " not found"));
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto deleteUser(long userId) {
        Optional<User> userOptional = userDao.findById(userId);
        User deletedUser = userOptional
                .map(user -> {
                    userDao.delete(user);
                    return user;
                })
                .orElseThrow(() -> new UnmodifiableSetException("User with " + userId + " not found"));
        return modelMapper.map(deletedUser, UserDto.class);
    }
}
