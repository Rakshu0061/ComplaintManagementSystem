package com.example.cms.security;

import com.example.cms.entity.User;
import com.example.cms.exception.ResourceNotFoundException;
import com.example.cms.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomeUserDetailsService implements UserDetailsService {

   @Autowired
   private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userRepo.findByUserName(username).orElseThrow(
            ()->new ResourceNotFoundException("User","userName : "+username,0)
        );

        return new org.springframework.security.core.userdetails.User(
            user.getUserName(),
            user.getPassword(),
            user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoles()))
                .collect(Collectors.toList())
        );
    }
}
