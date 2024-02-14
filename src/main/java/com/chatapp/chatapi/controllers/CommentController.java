package com.chatapp.chatapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.chatapi.entities.Comment;
import com.chatapp.chatapi.entities.User;
import com.chatapp.chatapi.exceptions.CommentException;
import com.chatapp.chatapi.exceptions.PostException;
import com.chatapp.chatapi.exceptions.UserException;
import com.chatapp.chatapi.services.CommentService;
import com.chatapp.chatapi.services.UserService;

@RestController
@CrossOrigin("*")

@RequestMapping("/api/comments")
public class CommentController {
    
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
   

    @PostMapping("/create/{postId}")
    private ResponseEntity<Comment>createCommentHandler(@RequestBody Comment comment,@PathVariable Long postId,@RequestHeader("Authorization") String token) throws UserException, PostException  {
       
        User user=userService.findUserProfile(token);

        Comment createdComment=commentService.createComment(comment, postId, user.getId());

        return new ResponseEntity<Comment>(createdComment,HttpStatus.OK);
    }

    @PutMapping("/like/{commentId}")
    private ResponseEntity<Comment> likeCommentHandler( @RequestHeader("Authorization") String token,@PathVariable Long commentId) throws UserException, CommentException {

        User user = userService.findUserProfile(token);

        Comment comment = commentService.likeComment(commentId, user.getId());

        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }

    @PutMapping("/unlike/{commentId}")
    private ResponseEntity<Comment> unlikeCommentHandler(@RequestHeader("Authorization") String token,
            @PathVariable Long commentId) throws UserException, CommentException {

        User user = userService.findUserProfile(token);

        Comment comment = commentService.unlikeComment(commentId, user.getId());

        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }
}
