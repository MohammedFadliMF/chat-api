package com.chatapp.chatapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.chatapi.entities.User;
import com.chatapp.chatapi.exceptions.UserException;
import com.chatapp.chatapi.response.MessageResponse;
import com.chatapp.chatapi.services.UserServiceIMPL;
@RestController
 @CrossOrigin("*")

@RequestMapping("/api/users")
public class UserController {
  
    @Autowired
    private UserServiceIMPL userServiceIMPL;

    @GetMapping("/id/{id}")
    public ResponseEntity<User> findUserByIdHandler(@PathVariable Long id) throws UserException{
        User user=userServiceIMPL.findUserById(id);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> findUserByUsernameHandler(@PathVariable String username) throws UserException {
        User user = userServiceIMPL.findUserByUsername(username);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    
    @PutMapping("/follow/{followUserId}")
    public ResponseEntity<MessageResponse> followUserHandler(@PathVariable Long followUserId,@RequestHeader("Authorization") String token) throws UserException {
        User user=userServiceIMPL.findUserProfile(token);
        String message=userServiceIMPL.followUser(user.getId(), followUserId);
        MessageResponse res=new MessageResponse(message);
        return new ResponseEntity<MessageResponse>(res,HttpStatus.OK);
    }

    @PutMapping("/unfollow/{userId}")
    public ResponseEntity<MessageResponse> unfollowUserHandler(@PathVariable Long userId, @RequestHeader("Authorization") String token)throws UserException {
        User user=userServiceIMPL.findUserProfile(token);
        String message=userServiceIMPL.unfollowUser(user.getId(), userId);
        MessageResponse res=new MessageResponse(message);
        return new ResponseEntity<MessageResponse>(res,HttpStatus.OK);
    }

    @PutMapping("/req")
    public ResponseEntity<User> findUserProfileHandler(@RequestHeader("Authorization") String token) throws UserException {
        User user = userServiceIMPL.findUserProfile(token);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/m/{userIds}")
    public ResponseEntity<List<User>> findUserByUserIdsHandler(@PathVariable List<Long> userIds) throws UserException {
        List<User> users = userServiceIMPL.findUsersByIds(userIds);
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }
    // api/users/search?q="query"
    @GetMapping("/search")
    public ResponseEntity<List <User>> searchUserHandler(@RequestParam("q") String query) throws UserException{
        List<User> users=userServiceIMPL.searchUser(query);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
    
    @PutMapping("/account/edit")
    public ResponseEntity<User> updateUserHandler(@RequestHeader("Authorization") String token,@RequestBody User user) throws UserException {
        User userReq = userServiceIMPL.findUserProfile(token);
        User updateUser=userServiceIMPL.updateUser(user, userReq);
        return new ResponseEntity<User>(updateUser, HttpStatus.OK);
    }
    


}
