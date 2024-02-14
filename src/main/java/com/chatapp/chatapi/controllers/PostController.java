package com.chatapp.chatapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.chatapi.entities.Post;
import com.chatapp.chatapi.entities.User;
import com.chatapp.chatapi.exceptions.PostException;
import com.chatapp.chatapi.exceptions.UserException;
import com.chatapp.chatapi.response.MessageResponse;
import com.chatapp.chatapi.services.PostService;
import com.chatapp.chatapi.services.UserService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@CrossOrigin("*")

@RequestMapping("/api/posts")

public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;


    @PostMapping("/create")
    public ResponseEntity<Post> createPostHandler(@RequestBody Post post,@RequestHeader("Authorization") String token) throws PostException, UserException{
        System.out.println("\n/////////////////  voila dans la creation d une post   ////////////////////");
        User user=userService.findUserProfile(token);
        Post createdPost=postService.createPost(post, user.getId());

        return new ResponseEntity<Post>(createdPost, HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<Post>> findPostByUserIdHandler(@PathVariable("id") Long userId) throws UserException{

        List<Post> posts=postService.findPostsByUserId(userId);

        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @GetMapping("/following/{ids}")
    public ResponseEntity<List<Post>> findAllPostByUserIdsHandler(@PathVariable("ids") List<Long> userIds) throws UserException, PostException {

        List<Post> posts = postService.findAllPostsByUserIds(userIds);

        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable("postId") Long postId)
            throws PostException {

        Post post = postService.findPostById(postId);

        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }

    @PutMapping("/like/{postId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable("postId") Long postId,@RequestHeader("Authorization") String token)
            throws UserException, PostException {
        
        User user =userService.findUserProfile(token);        
        Post likedpost = postService.likePost(postId,user.getId());

        return new ResponseEntity<Post>(likedpost, HttpStatus.OK);
    }
    
    @PutMapping("/unlike/{postId}")
    public ResponseEntity<Post> unlikePostHandler(@PathVariable("postId") Long postId,
            @RequestHeader("Authorization") String token)
            throws UserException, PostException {
        User user = userService.findUserProfile(token);
        Post unlikedpost = postService.unlikePost(postId, user.getId());

        return new ResponseEntity<Post>(unlikedpost, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<MessageResponse> deletePostHandler(@PathVariable("postId") Long postId,@RequestHeader("Authorization") String token)throws UserException, PostException {
        
        User user = userService.findUserProfile(token);
        String message = postService.deletePost(postId, user.getId());

        MessageResponse messageResponse=new MessageResponse(message);

        return new ResponseEntity<MessageResponse>(messageResponse, HttpStatus.ACCEPTED);
    }

    @PutMapping("/save_post/{postId}")
    public ResponseEntity<MessageResponse> savedPostHandler(@PathVariable("postId") Long postId,@RequestHeader("Authorization") String token) throws UserException, PostException {
       
        User user = userService.findUserProfile(token);
        String message = postService.savedPost(postId, user.getId());

        MessageResponse messageResponse = new MessageResponse(message);

        return new ResponseEntity<MessageResponse>(messageResponse, HttpStatus.ACCEPTED);
    }

    @PutMapping("/unsave_post/{postId}")
    public ResponseEntity<MessageResponse> unsavedPostHandler(@PathVariable("postId") Long postId,
            @RequestHeader("Authorization") String token) throws UserException, PostException {
        User user = userService.findUserProfile(token);
        String message = postService.unsavedPost(postId, user.getId());

        MessageResponse messageResponse = new MessageResponse(message);

        return new ResponseEntity<MessageResponse>(messageResponse, HttpStatus.ACCEPTED);
    }
    
}
