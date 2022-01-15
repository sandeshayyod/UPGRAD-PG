package org.example.user.dto;

import java.util.Set;

public class UserDto {

    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String dateOfBirth;
    private Set<Integer> phoneNumbers;

    public UserDto() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Integer> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<Integer> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
