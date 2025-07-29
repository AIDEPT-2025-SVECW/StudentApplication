package com.bvraju.aidept.StudentApplication.Service.impl;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bvraju.aidept.StudentApplication.model.User;
import com.bvraju.aidept.StudentApplication.repository.IUserJPARepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserJPARepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userInDB = userRepo.findByUserid(username);
        UserDetails userDetails = new UserDetails() {

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                // TODO Auto-generated method stub
                return Collections.singleton(new SimpleGrantedAuthority("USER"));

            }

            @Override
            public String getPassword() {
                return userInDB.getPassword();
            }

            @Override
            public String getUsername() {
                return userInDB.getUserid();
            }

        };
        return userDetails;
    }

}
