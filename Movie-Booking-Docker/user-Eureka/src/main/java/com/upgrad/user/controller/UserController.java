package com.upgrad.user.controller;

import com.upgrad.user.dto.UserDTO;
import com.upgrad.user.entities.User;
import com.upgrad.user.service.UserService;
import com.upgrad.user.utils.POJOConvertor;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "user_app/v1")
public class UserController {

  @Autowired
  private UserService userService ;

  /**
   * Create a POST API
   */

  @PostMapping(value="/users" , consumes = MediaType.APPLICATION_JSON_VALUE ,
  produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity createUser(@RequestBody  UserDTO userDTO){
    User  user = POJOConvertor.covertUserDTOToEntity(userDTO);

    User savedUser = userService.createUser(user);

    UserDTO savedUserDTO = POJOConvertor.covertUserEntityToDTO(user);

    return new ResponseEntity(savedUserDTO , HttpStatus.CREATED);
  }

  /**
   *
   * Get the list of all the users
   *
   * 127.0.0.1:8080/users_app/v1/users  GET
   */
  @GetMapping(value="/users" , produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity GetAllUsers(){
    List<User> userList = userService.getAllUsers();

    List<UserDTO>  userDTOList = new ArrayList<>();

    userList.forEach(u -> userDTOList.add(POJOConvertor.covertUserEntityToDTO(u)));

    return new ResponseEntity(userDTOList, HttpStatus.OK);

  }

  /**
   *
   * 127.0.0.1:8080/users_app/v1/users/{id}   GET
   *
   */
    @GetMapping(value = "/users/{id}")
    public ResponseEntity getUser(@PathVariable(name = "id")  int id){
       User user = (userService.getUserBasedOnId(id));

       if(user!=null) {
         UserDTO userDTO = POJOConvertor.covertUserEntityToDTO(user);
         return new ResponseEntity(userDTO, HttpStatus.OK);
       }else{
         return null;
       }
    }

  /**
   *
   *
   *
   * PUT
   *
   * 127.0.0.1:8080/users_app/v1/users/{id}
   * Request body will be present {}
   */
  @PutMapping(value="/users" , consumes = MediaType.APPLICATION_JSON_VALUE ,
  produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity updateUser( @RequestBody UserDTO userDTO){

    User userToBeUpdated = POJOConvertor.covertUserDTOToEntity(userDTO);
    User savedUpdatedUser = userService.updateUser(userToBeUpdated);

    UserDTO user = POJOConvertor.covertUserEntityToDTO(savedUpdatedUser);

    return new ResponseEntity(user, HttpStatus.ACCEPTED);
  }

  /**
   *
   *
   * 127.0.0.1:8080/user_app/v2/users/{id}  -- deletion
   */

  @DeleteMapping(value="/users/{id}")
  public ResponseEntity deleteUser(@PathVariable(name="id") int id){

    User user = userService.getUserBasedOnId(id);
    userService.deleteUser(user);

    return  new ResponseEntity(null, HttpStatus.OK);
  }

}