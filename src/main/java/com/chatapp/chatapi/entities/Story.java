package com.chatapp.chatapi.entities;

import java.time.LocalDateTime;

import com.chatapp.chatapi.dtos.UserDto;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor

public class Story {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "id",column = @Column(name="user_id")),
        @AttributeOverride(name = "email", column =@Column (name = "user_email")),

    })
    private UserDto user;

    @NotNull
    private String storyimage;
    private String caption;
    private LocalDateTime timestamp;
}
