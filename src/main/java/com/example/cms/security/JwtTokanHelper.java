package com.example.cms.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokanHelper {

    public final long jwt_token_validity=5*60*60*1000;

    private final Key key= Keys.secretKeyFor(SignatureAlgorithm.HS256);

    //generate token

    public String generateToken(String username) {

        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+jwt_token_validity))
            .signWith(key)
            .compact();

    }

    //GET USERNAME FROM TOKEN

    public String extractUsername(String token) {

        return extractAllClaims(token).getSubject();

    }


    // check if token is expired
    public boolean isTokenExpired(String token)
    {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    //validate token

    public boolean validateToken(String token, String userName) {
        return (extractUsername(token).equals(userName) && !isTokenExpired(token));
    }

    private Claims extractAllClaims(String token) {
        // TODO Auto-generated method stub


        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}
