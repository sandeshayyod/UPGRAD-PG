package com.example.aopdemo.security.dao;

import com.example.aopdemo.security.model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

import static com.example.aopdemo.security.enums.ApplicationRole.*;

@Repository
public class ApplicationUserDetailDao {

    @Autowired
    PasswordEncoder encoder;

    public ApplicationUser findByUsername(String username) {
        return loadAllUsers().stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    private List<ApplicationUser> loadAllUsers() {
        return Arrays.asList(
                ApplicationUser.builder().username("admin").password(encoder.encode("password"))
                        .authorities(ADMIN.getGrantedAuthorities()).build(),
                ApplicationUser.builder().username("manager").password(encoder.encode("password"))
                        .authorities(MANAGER.getGrantedAuthorities()).build(),
                ApplicationUser.builder().username("sales").password(encoder.encode("password"))
                        .authorities(SALES.getGrantedAuthorities()).build()
        );

    }
}
