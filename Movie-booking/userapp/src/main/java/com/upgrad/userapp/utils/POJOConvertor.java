package com.upgrad.userapp.utils;

import com.upgrad.userapp.dto.UserDTO;
import com.upgrad.userapp.entities.User;


public class POJOConvertor {

  public static UserDTO covertUserEntityToDTO(User user) {
    UserDTO userDTO = new UserDTO();
    userDTO.setDateOfBirth(user.getDateOfBirth());
    userDTO.setFirstName(user.getFirstName());
    userDTO.setLastName(user.getLastName());
    userDTO.setPassword(user.getPassword());
    userDTO.setPhoneNumbers(user.getPhoneNumbers());
    userDTO.setUserId(user.getUserId());
    userDTO.setUsername(user.getUsername());

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
    user.setUsername(userDTO.getUsername());

    return user;
  }
}
