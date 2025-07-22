package com.example.cms.controller;

import com.example.cms.entity.User;
import com.example.cms.helper.JwtAuthRequest;
import com.example.cms.helper.JwtAuthResponse;
import com.example.cms.security.JwtTokanHelper;
import com.example.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtTokanHelper jwtTokanHelper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager manager;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user)
    {
        String message= userService.registerUser(user);
        return ResponseEntity.ok(message);
    }
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) {
        System.out.println("Login attempt for: " + request.getUserName());
        authenticate(request.getUserName(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());

        String token = jwtTokanHelper.generateToken(userDetails.getUsername());
        System.out.println("✅ Token generated: " + token);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    private void authenticate(String userName, String password) {
        try {
            System.out.println("Authenticating: " + userName + " with password: " + password);
            manager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        } catch (BadCredentialsException e) {
            System.out.println(" Bad credentials for user: " + userName);
            throw new RuntimeException("Invalid username or password");
        }
    }


}
