package com.upgrad.bookmyshow.userapp.controller;

import com.upgrad.bookmyshow.userapp.dto.UserDto;
import com.upgrad.bookmyshow.userapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/userapp/v1")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/hello/{name}", "/hello", "/"})
    public String sayHello(@PathVariable(name = "name", required = false) String name) {
        String welcome = "Welcome" + (name == null ? "" : " " + name) + "!";
        return "Hi! the UserApp is live and able to respond. " + welcome;
    }

    @PostMapping(value = "/users", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        UserDto savedUserDto = userService.saveUser(userDto);
        return new ResponseEntity<>(savedUserDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/users", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        UserDto updatedUserDto = userService.updateUser(userDto);
        return new ResponseEntity<>(updatedUserDto, HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/users/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUser(@PathVariable(name = "id") String id) {
        UserDto userDto = userService.getUser(Long.parseLong(id));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> removeUser(@PathVariable String id) {
        UserDto userDto = userService.deleteUser(Long.parseLong(id));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}
