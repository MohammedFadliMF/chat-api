package com.chatapp.chatapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatapp.chatapi.entities.Post;

public interface PostRepository  extends JpaRepository<Post,Long>{
    
    @Query("select p from Post p where p.user.id=?1")
    public List<Post> findPostByUserId(Long userId);
    
    @Query("select p from Post p where p.user.id IN :userIds Order By p.createdAt DESC")
    public List<Post> findAllPostByUserIds(@Param("userIds") List<Long> userIds);
 
}
