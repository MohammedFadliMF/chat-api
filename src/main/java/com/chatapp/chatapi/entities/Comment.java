package com.chatapp.chatapi.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.chatapp.chatapi.dtos.UserDto;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor

public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;


    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "id",column = @Column(name="user_id")),
        @AttributeOverride(name = "email", column =@Column (name = "user_email")),

    })
    private UserDto user;

    @Embedded
    @ElementCollection
    private Set<UserDto> likedByUsers=new HashSet<>();

    private LocalDateTime createdAt;
   
}
