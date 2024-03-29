package com.chatapp.chatapi.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component                                                                                          
public class JwtTokenGeneratorFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
                if (authentication!=null) {
                    SecretKey key =Keys.hmacShaKeyFor(SecurityContext.JWT_KEY.getBytes());

                 String jwt=Jwts.builder()
                    .setIssuer("chatapp")
                    .setIssuedAt(new Date())
                    .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                    .claim("username", authentication.getName())
                    .setExpiration(new Date(new Date().getTime()+30000000))
                    .signWith(key).compact();
                  
                 response.setHeader(SecurityContext.HEADER, jwt);   

                }
                filterChain.doFilter(request, response);
            }
    public String populateAuthorities(Collection<? extends GrantedAuthority> collection){
        Set <String> autorities=new HashSet<>();
        for (GrantedAuthority authority : collection) {
            autorities.add(authority.getAuthority());
        }
        return String.join(",", autorities);
    }
    protected boolean shouldNotFilter(HttpServletRequest req)throws ServletException{
        return !req.getServletPath().equals("/signin");
    }

    
}
