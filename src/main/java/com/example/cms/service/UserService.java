package com.example.cms.service;

import com.example.cms.entity.Roles;
import com.example.cms.entity.User;
import com.example.cms.repository.RoleRepo;
import com.example.cms.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(User user)
    {
        Optional<User> existingUser=userRepo.findByUserName(user.getUserName());
        if (existingUser.isPresent())
        {
            throw new RuntimeException("user already exist");
        }
        Set<Roles> userRole=new HashSet<>();
        for (Roles roles:user.getRoles())
        {
            Roles existingRoles=roleRepo.findByRoles(roles.getRoles())
                .orElseGet(()->roleRepo.save(new Roles(roles.getRoles())));
            userRole.add(existingRoles);
        }

        user.setRoles(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "User Registered Successfully";
    }

}
