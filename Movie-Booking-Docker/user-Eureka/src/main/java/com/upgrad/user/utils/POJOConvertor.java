package com.upgrad.user.utils;

import com.upgrad.user.dto.UserDTO;
import com.upgrad.user.entities.User;


public class POJOConvertor {

  public static UserDTO covertUserEntityToDTO(User user) {
    UserDTO userDTO = new UserDTO();
    userDTO.setDateOfBirth(user.getDateOfBirth());
    userDTO.setFirstName(user.getFirstName());
    userDTO.setLastName(user.getLastName());
    userDTO.setPassword(user.getPassword());
    userDTO.setPhoneNumbers(user.getPhoneNumbers());
    userDTO.setUserId(user.getUserId());
    userDTO.setUsername(user.getUserName());

    return userDTO;
  }

  public static User covertUserDTOToEntity(UserDTO userDTO) {
    User user = new User();
    user.setDateOfBirth(userDTO.getDateOfBirth());
    user.setFirstName(userDTO.getFirstName());
    user.setLastName(userDTO.getLastName());
    user.setPassword(userDTO.getPassword());
    user.setPhoneNumbers(userDTO.getPhoneNumbers());
    user.setUserId(userDTO.getUserId());
    user.setUserName(userDTO.getUsername());

    return user;
  }
}
