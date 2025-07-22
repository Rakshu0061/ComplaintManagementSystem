package com.example.cms.entity;

import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;

import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

private  String userName;
private String  password;


@ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
inverseJoinColumns = @JoinColumn(name = "role_id"))
private Set<Roles> roles=new HashSet<>();


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }
}
