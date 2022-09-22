package com.example.RedditClone.repository;

import com.example.RedditClone.model.Comment;
import com.example.RedditClone.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {


    @Query("SELECT * FROM Comment c WHERE c.post = ?1")
    List<Comment> findByPost(Post post);
}
