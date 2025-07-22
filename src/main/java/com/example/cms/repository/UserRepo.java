package com.example.cms.repository;

import com.example.cms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {

   // Optional<User> findByName(String userName);
    Optional<User> findByUserName(String userName);

}
