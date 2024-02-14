package com.chatapp.chatapi.services;

import java.util.List;

import com.chatapp.chatapi.entities.Post;
import com.chatapp.chatapi.exceptions.PostException;
import com.chatapp.chatapi.exceptions.UserException;

public interface PostService {

    public Post createPost(Post post,Long userId)throws UserException;
    
    public String deletePost(Long postId,Long userId) throws UserException,PostException;
    
    public List<Post>  findPostsByUserId(Long userId) throws UserException;
    
    public Post findPostById(Long postId) throws PostException;
    
    public List<Post> findAllPostsByUserIds(List <Long> userIds) throws UserException,PostException;
    
    public String  savedPost(Long postId,Long userId) throws UserException,PostException;
    
    public String unsavedPost(Long postId, Long userId) throws UserException, PostException;
    
    public Post likePost(Long postId, Long userId) throws UserException, PostException;
    
    public Post unlikePost(Long postId, Long userId) throws UserException, PostException;


}
