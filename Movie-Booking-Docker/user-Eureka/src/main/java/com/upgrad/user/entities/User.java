package com.upgrad.user.entities;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int userId;

  @Column(name = "first_name", length = 20, nullable = false)
  private String firstName;

  @Column(length = 20)
  private String lastName;

  @Column(name = "user_name",length = 20, nullable = false, unique = true)
  private String userName;

  @Column(length = 20, nullable = true)
  private String password;

  @Column(nullable = true)
  private LocalDateTime dateOfBirth;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "customer_contact_number", joinColumns = @JoinColumn(name = "customer_id"))
  @Column(name = "mobile_number", nullable = true)
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

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
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
}