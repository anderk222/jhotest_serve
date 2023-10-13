package com.anderk222.jhotest.util;

import java.util.Collections;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TokenUtil {

    private SecretKey secretKey;

    private final static long expiration = 121212121l;

    public String createToken(String username) {

        Date tokenExpiration = new Date(System.currentTimeMillis() + expiration);


        return Jwts.builder()
                .subject(username)
                .expiration(tokenExpiration)
                .signWith(secretKey)
                .compact();
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {

        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        String name = claims.getSubject();

        return new UsernamePasswordAuthenticationToken(name,null, Collections.emptyList());

    }

}