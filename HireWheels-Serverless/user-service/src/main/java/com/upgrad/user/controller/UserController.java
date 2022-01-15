package com.upgrad.user.controller;

import com.upgrad.user.dto.UserDTO;
import com.upgrad.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.createUser(userDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping(path = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUserByID(@PathVariable(name = "id") String id) {
        UserDTO userDTO = userService.getUserByID(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOList = userService.getAllUsers();
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<Boolean> deleteUserByID(@PathVariable(name = "id") String id) {
        userService.deleteUserByID(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
