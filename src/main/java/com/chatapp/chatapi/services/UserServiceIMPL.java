package com.chatapp.chatapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chatapp.chatapi.dtos.UserDto;
import com.chatapp.chatapi.entities.User;
import com.chatapp.chatapi.exceptions.UserException;
import com.chatapp.chatapi.repositories.UserRepository;
import com.chatapp.chatapi.securityconfig.JwtTokenClaims;
import com.chatapp.chatapi.securityconfig.JwtTokenProvider;

@Service
public class UserServiceIMPL implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public User registerUser(User user) throws UserException {
        Optional<User> isEmailExist=userRepository.findByEmail(user.getEmail());
        if (isEmailExist.isPresent()) {
            throw new UserException("Email Is Already Exist");
        } 
        
        Optional<User> isUsernameExist = userRepository.findByUsername(user.getUsername());
        if (isUsernameExist.isPresent()) {
            throw new UserException("Username Is Already Taken");
        }

        if (user.getEmail()==null || user.getUsername()==null || user.getPassword()==null) {
            throw new UserException("All filds are required!");
        }

        User newUser =new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());

        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(newUser);
    }

    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> user=userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UserException("User Not Exist With Id: "+userId);


        // Optional<User> optionalUser = userRepository.findById(userId);
        // return optionalUser.orElseThrow(() -> new UserException("User Not Exist With Id: " + userId));


        // User user2=userRepository.findById(userId).orElse(null);
        // if (user2==null) {
        //     throw new UserException("User Not Exist With Id: " + userId);
        // }
        // return user2;

    }

    @Override
    public User findUserProfile(String token) throws UserException {
        System.out.println("token :  "+token);
        token=token.substring(7);

        JwtTokenClaims jwtTokenClaims=jwtTokenProvider.getClaimsFromToken(token);

        //in JwtGeneratorFilter we added in claims username as email
        String email=jwtTokenClaims.getUsername();
        Optional<User> opt=userRepository.findByEmail(email);
       
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new UserException("token invalid");
        
    }

    @Override
    public  User findUserByUsername(String username) throws UserException {
        Optional<User>  user=userRepository.findByUsername(username);  
        if (user.isPresent() ) {
            return user.get();
        }
        throw new UserException("User Not Exist With Name: "+username);  
    }

    @Override
    public String followUser(Long req_userId, Long follow_userId) throws UserException {
       
        User reqUser=findUserById(req_userId);//+followings

        User followUser=findUserById(follow_userId);//+followers

        UserDto follower=new UserDto();

        follower.setEmail(reqUser.getEmail());
        follower.setUsername(reqUser.getUsername());
        follower.setImage(reqUser.getImage());
        follower.setId(reqUser.getId());

        UserDto following=new UserDto();
        
        following.setEmail(followUser.getEmail());
        following.setUsername(followUser.getUsername());
        following.setImage(followUser.getImage());
        following.setId(followUser.getId());

        reqUser.getFollowings().add(following);
        followUser.getFollowers().add(follower);

        userRepository.save(followUser);
        userRepository.save(reqUser);

        return "you are following "+followUser.getUsername();

    }

    @Override
    public String unfollowUser(Long req_userId, Long unfollow_userId) throws UserException {
        
        User reqUser = findUserById(req_userId);// +followings

        User followUser = findUserById(unfollow_userId);// +followers

        UserDto follower = new UserDto();
        follower.setEmail(reqUser.getEmail());
        follower.setUsername(reqUser.getUsername());
        follower.setImage(reqUser.getImage());
        follower.setId(reqUser.getId());

        UserDto following = new UserDto();
        following.setEmail(followUser.getEmail());
        following.setUsername(followUser.getUsername());
        following.setImage(followUser.getImage());
        following.setId(followUser.getId());

        //you must add cheking exicting of followers and followings then remove 

        reqUser.getFollowings().remove(following);
        followUser.getFollowers().remove(follower);

        userRepository.save(followUser);
        userRepository.save(reqUser);

        return "you have unfollowed " + followUser.getUsername();

      }

    @Override
    public List<User> findUsersByIds(List<Long> usersIds) throws UserException {
        List <User> users=userRepository.findAllUsersByUsersIds(usersIds);
        return users;
    }

    @Override
    public List<User> searchUser(String query) throws UserException {
        List <User> users=userRepository.findByQuery(query);
        if (users.size()==0) {
            throw new UserException("User Not Found");
        }
        return users;
    }

    @Override
    public User updateUser(User newUser, User oldUser) throws UserException {
        if (newUser.getId()==null || !newUser.getId().equals(oldUser.getId())) {
            throw new UserException("Not The Same Users");
        }
        if (newUser.getUsername() != null && !newUser.getUsername().isBlank()
                && newUser.getEmail() != null && !newUser.getEmail().isBlank()
                && newUser.getPassword() != null && !newUser.getPassword().isBlank()) {
                oldUser.setId(newUser.getId());
                oldUser.setUsername(newUser.getUsername());
                oldUser.setEmail(newUser.getEmail());
                oldUser.setPassword(newUser.getPassword());
                oldUser.setMobile(newUser.getMobile());
                oldUser.setWebsite(newUser.getWebsite());
                oldUser.setBio(newUser.getBio());
                oldUser.setGender(newUser.getGender());
                oldUser.setImage(newUser.getImage());

                User updatedUser=userRepository.save(oldUser);
                if (updatedUser !=null) {
                    return updatedUser;
                }else{
                    throw new UserException("Failed To update user.");
                }
        }else{
            throw new UserException("invalid data for update.");
        }
     }
    
}
