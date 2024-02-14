package com.chatapp.chatapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatapp.chatapi.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long>{

    
}
