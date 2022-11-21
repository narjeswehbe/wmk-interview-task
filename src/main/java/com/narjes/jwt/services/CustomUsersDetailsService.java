package com.narjes.jwt.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class CustomUsersDetailsService implements UserDetailsService {
    //validates the user
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.equals("Narjes"))
        {
            return new User("Narjes" , "secret" , new ArrayList<>());
        }else {
            throw new UsernameNotFoundException("user name not found");
        }

    }
}
