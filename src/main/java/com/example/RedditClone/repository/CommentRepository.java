package com.example.RedditClone.repository;

import com.example.RedditClone.model.Comment;
import com.example.RedditClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {


    @Query(value = "SELECT * FROM Comment c WHERE c.post_id = ?1", nativeQuery = true)
    List<Comment> findByPost(Long postId);

    @Query(value = "SELECT * FROM Comment c WHERE c.user = ?1", nativeQuery = true)
    List<Comment> findCommentByUser(User user);
}
