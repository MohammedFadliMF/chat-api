package com.chatapp.chatapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chatapp.chatapi.repositories.UserRepository;
@Service
public class UserUserDetailsService implements UserDetailsService{
   
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<com.chatapp.chatapi.entities.User> opt=userRepository.findByEmail(username);
       
        if (opt.isPresent()) {

            com.chatapp.chatapi.entities.User user=opt.get();

            List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
            
            return new User(user.getEmail(), user.getPassword(), authorities);
        }
        throw new BadCredentialsException("user not found with username: "+username);
    }
    
}
