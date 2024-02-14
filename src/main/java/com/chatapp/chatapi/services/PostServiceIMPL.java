package com.chatapp.chatapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatapp.chatapi.dtos.UserDto;
import com.chatapp.chatapi.entities.Post;
import com.chatapp.chatapi.entities.User;
import com.chatapp.chatapi.exceptions.PostException;
import com.chatapp.chatapi.exceptions.UserException;
import com.chatapp.chatapi.repositories.PostRepository;
import com.chatapp.chatapi.repositories.UserRepository;

@Service
public class PostServiceIMPL implements PostService{


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public Post createPost(Post post,Long userId) throws UserException {
    
        User user=userService.findUserById(userId);

        UserDto userDto=new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setImage(user.getImage());
        userDto.setUsername(user.getUsername());

        post.setUser(userDto);
        Post createdPost=postRepository.save(post);
        return createdPost;
    }

    @Override
    public String deletePost(Long postId, Long userId) throws UserException, PostException {
    
        Post post=findPostById(postId);

        User user=userService.findUserById(userId);

        if (post.getUser().getId().equals(user.getId())) {
            postRepository.deleteById(post.getId());
            return "Post Deleted successfully";
        }
        throw new PostException("You can't delete other user's post");

    }

    @Override
    public List<Post> findPostsByUserId(Long userId) throws UserException {

        List<Post> posts=postRepository.findPostByUserId(userId);
        if (posts.size()==0) {
            throw new UserException("this User does not have any post");
        }
        return posts;
    }

    @Override
    public Post findPostById(Long postId) throws PostException {

        Optional <Post> opt=postRepository.findById(postId);

        if (opt.isPresent()) {
            return opt.get();
        }
        throw new PostException("Post not found with id: "+postId);
    }

    @Override
    public List<Post> findAllPostsByUserIds(List<Long> userIds) throws UserException, PostException {

        List <Post> posts=postRepository.findAllPostByUserIds(userIds);
        if (posts.size()==0) {
            throw new PostException("Posts Not Found");
        }
        return posts;
    }

    @Override
    public String savedPost(Long postId, Long userId) throws UserException, PostException {

        Post post=findPostById(postId);

        User user=userService.findUserById(userId);

        if (!user.getSavedPost().contains(post)) {
            user.getSavedPost().add(post); 
            userRepository.save(user);
        }
        return "Post Saved Successfully";
    }

    @Override
    public String unsavedPost(Long postId, Long userId) throws UserException, PostException {
        Post post = findPostById(postId);

        User user = userService.findUserById(userId);

        if (user.getSavedPost().contains(post)) {
            user.getSavedPost().remove(post);
            userRepository.save(user);
        }
        return "Post UnSaved Successfully";
    }

    @Override
    public Post likePost(Long postId, Long userId) throws UserException, PostException {

        Post post=findPostById(postId);
        User user=userService.findUserById(userId);

        UserDto userDto = new UserDto();

        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setImage(user.getImage());
        userDto.setUsername(user.getUsername());
        
        post.getLikedByUsers().add(userDto);

        return postRepository.save(post);
    }

    @Override
    public Post unlikePost(Long postId, Long userId) throws UserException, PostException {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        UserDto userDto = new UserDto();

        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setImage(user.getImage());
        userDto.setUsername(user.getUsername());
        post.getLikedByUsers().remove(userDto);

        return postRepository.save(post);    }
    
}
