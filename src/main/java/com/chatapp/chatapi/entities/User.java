package com.chatapp.chatapi.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.chatapp.chatapi.dtos.UserDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    
    private String mobile;
    private String website;
    private String bio;
    private String gender;
    private String image;
    

    @Embedded
    @ElementCollection
    private Set<UserDto>followers=new HashSet<UserDto>();

    @Embedded
    @ElementCollection
    private Set<UserDto> followings = new HashSet<UserDto>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Story> stories=new ArrayList<>();

    @ManyToMany()
    private List<Post> savedPost=new ArrayList<>();
    

}
