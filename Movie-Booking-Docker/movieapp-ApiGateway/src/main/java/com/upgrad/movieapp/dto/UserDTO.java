package com.upgrad.movieapp.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class UserDTO {

  private int userId;
  private String firstName;

  private String lastName;

  private String username;

  private String password;

  private LocalDateTime dateOfBirth;

  private Set<Integer> phoneNumbers;

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public LocalDateTime getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDateTime dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Set<Integer> getPhoneNumbers() {
    return phoneNumbers;
  }

  public void setPhoneNumbers(Set<Integer> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
  }

  @Override
  public String toString() {
    return "UserDTO{" + "userId=" + userId + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
        + ", username='" + username + '\'' + ", password='" + password + '\'' + ", dateOfBirth=" + dateOfBirth
        + ", phoneNumbers=" + phoneNumbers + '}';
  }
}
