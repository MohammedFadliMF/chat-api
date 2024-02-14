package com.chatapp.chatapi.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatapp.chatapi.dtos.UserDto;
import com.chatapp.chatapi.entities.Comment;
import com.chatapp.chatapi.entities.Post;
import com.chatapp.chatapi.entities.User;
import com.chatapp.chatapi.exceptions.CommentException;
import com.chatapp.chatapi.exceptions.PostException;
import com.chatapp.chatapi.exceptions.UserException;
import com.chatapp.chatapi.repositories.CommentRepository;
import com.chatapp.chatapi.repositories.PostRepository;

@Service
public class CommentServiceIMPL implements CommentService{
    
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment createComment(Comment comment, Long postId, Long userId) throws UserException, PostException {
    
        User user=userService.findUserById(userId);
        Post post=postService.findPostById(postId);

        UserDto userDto=new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setImage(user.getImage());
        userDto.setUsername(user.getUsername());

        comment.setUser(userDto);
        comment.setCreatedAt(LocalDateTime.now());

        Comment createdComment=commentRepository.save(comment);

        post.getComents().add(createdComment);
        postRepository.save(post);
        return createdComment;

    }

    @Override
    public Comment findCommentById(Long commentId) throws CommentException {
    
        Optional<Comment> opt=commentRepository.findById(commentId);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new CommentException("Comment is not exict with id: "+commentId);
    }

    @Override
    public Comment likeComment(Long commentId, Long userId) throws CommentException, UserException {
       
        User user=userService.findUserById(userId);
        Comment comment=findCommentById(commentId);

        UserDto userDto=new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setImage(user.getImage());
        userDto.setUsername(user.getUsername());

        comment.getLikedByUsers().add(userDto);
        return commentRepository.save(comment);
    }

    @Override
    public Comment unlikeComment(Long commentId, Long userId) throws CommentException, UserException {
        User user = userService.findUserById(userId);
        Comment comment = findCommentById(commentId);

        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setImage(user.getImage());
        userDto.setUsername(user.getUsername());

        comment.getLikedByUsers().remove(userDto);
        return commentRepository.save(comment);
    }
    
}
