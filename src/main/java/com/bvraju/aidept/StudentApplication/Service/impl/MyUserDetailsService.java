package com.bvraju.aidept.StudentApplication.Service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bvraju.aidept.StudentApplication.configuration.ROLE;
import com.bvraju.aidept.StudentApplication.model.MyUser;
import com.bvraju.aidept.StudentApplication.repository.IUserJPARepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserJPARepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser userInDB = userRepo.findByUserid(username);
        ROLE userRole = userInDB.getRole();

        UserDetails user1 = new UserDetails() {

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                Set<SimpleGrantedAuthority> authorities = new HashSet<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.name()));
                authorities.addAll(userRole.getPermissions()
                        .stream().map(currPermission -> new SimpleGrantedAuthority(currPermission.name()))
                        .collect(Collectors.toSet()));
                return authorities;

            }

            private Object map(Object object) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'map'");
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
        return user1;
    }

}
