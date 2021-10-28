package com.upgrad.userapp.controller;

import com.upgrad.userapp.dto.UserDTO;
import com.upgrad.userapp.entities.User;
import com.upgrad.userapp.service.UserService;
import com.upgrad.userapp.utils.POJOConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/user-app/v1")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * post api
     */
    @PostMapping(value = "/users", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = POJOConvertor.covertUserDTOToEntity(userDTO);
        User savedUser = userService.createUser(user);
        UserDTO savedUserDTO = POJOConvertor.covertUserEntityToDTO(savedUser);
        return new ResponseEntity<>(savedUserDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/users", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDTO> userDtos = users.stream()
                .map(POJOConvertor::covertUserEntityToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUserBasedOnId(@PathVariable(name = "id") int id) {
        User user = userService.getUserBasedOnId(id);
        UserDTO userDTO = POJOConvertor.covertUserEntityToDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/users", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        User user = POJOConvertor.covertUserDTOToEntity(userDTO);
        User updatedUser = userService.updateUser(user);
        UserDTO updatedUserDTO = POJOConvertor.covertUserEntityToDTO(updatedUser);
        return new ResponseEntity<>(updatedUserDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/users/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> deleteUser(@PathVariable(name = "id") int id) {
        User user = userService.getUserBasedOnId(id);
        userService.deleteUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}