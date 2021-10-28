package com.upgrad.userapp.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int userId;

  @Column(name = "first_name", length = 20, nullable = false)
  private String firstName;

  @Column(length = 20)
  private String lastName;

  @Column(length = 20, nullable = false, unique = true)
  private String username;

  @Column(length = 20, nullable = false)
  private String password;

  @Column(nullable = false)
  private LocalDateTime dateOfBirth;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "customer_contact_number", joinColumns = @JoinColumn(name = "customer_id"))
  @Column(name = "mobile_number", nullable = false)
  private Set<Integer> phoneNumbers;
}