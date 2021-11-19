package com.example.aopdemo.security.service;

import com.example.aopdemo.security.dao.ApplicationUserDetailDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplicationUserDetailService implements UserDetailsService {
    private final ApplicationUserDetailDao dao;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        return dao.findByUsername(userName);
    }
}