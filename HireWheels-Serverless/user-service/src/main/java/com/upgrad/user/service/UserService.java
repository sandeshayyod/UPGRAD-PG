package com.upgrad.user.service;

import com.upgrad.user.dto.UserDTO;
import com.upgrad.user.entity.User;
import com.upgrad.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    public UserDTO getUserByID(String id) {
        Optional<User> savedUserOptional = userRepo.findById(id);
        return savedUserOptional.map(user -> modelMapper.map(user, UserDTO.class)).orElse(null);
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOList = new ArrayList<>();
        Iterable<User> userList = userRepo.findAll();
        for (User user : userList) {
            userDTOList.add(modelMapper.map(user, UserDTO.class));
        }
        return userDTOList;
    }

    public void deleteUserByID(String id) {
        userRepo.deleteById(id);
    }
}
