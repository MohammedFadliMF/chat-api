package com.chatapp.chatapi.securityconfig;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.chatapp.chatapi.security.SecurityContext;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtTokenProvider {
    
    public JwtTokenClaims getClaimsFromToken(String token){
       
        SecretKey key=Keys.hmacShaKeyFor(SecurityContext.JWT_KEY.getBytes());

        Claims claims=Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();

        String username=String.valueOf(claims.get("username"));

        JwtTokenClaims jwtTokenClaims=new JwtTokenClaims();

        jwtTokenClaims.setUsername(username);

        return jwtTokenClaims;
    }
}
