package com.chatapp.chatapi.services;

import java.util.List;

import com.chatapp.chatapi.entities.User;
import com.chatapp.chatapi.exceptions.UserException;

public interface UserService {
    public User registerUser(User user) throws UserException;
    
    public User findUserById(Long userId) throws UserException;
    
    public User findUserProfile(String token) throws UserException;
    
    public User findUserByUsername(String username) throws UserException;
    
    public String followUser(Long req_userId,Long follow_userId) throws UserException;
    
    public String unfollowUser(Long req_userId, Long unfollow_userId) throws UserException;
    
    public List<User> findUsersByIds(List <Long> usersIds) throws UserException;
    
    public List<User> searchUser(String query) throws UserException;
    
    public User updateUser(User newUser,User oldUser) throws UserException;

}
