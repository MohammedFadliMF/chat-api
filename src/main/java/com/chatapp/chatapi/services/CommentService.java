package com.chatapp.chatapi.services;

import com.chatapp.chatapi.entities.Comment;
import com.chatapp.chatapi.exceptions.CommentException;
import com.chatapp.chatapi.exceptions.PostException;
import com.chatapp.chatapi.exceptions.UserException;

public interface CommentService {

    public Comment createComment(Comment comment,Long postId,Long userId) throws UserException,PostException;
    
    public Comment findCommentById(Long commentId) throws CommentException;
    
    public Comment likeComment(Long commentId,Long userId) throws CommentException,UserException;
    
    public Comment unlikeComment(Long commentId, Long userId) throws CommentException, UserException;

    
}
