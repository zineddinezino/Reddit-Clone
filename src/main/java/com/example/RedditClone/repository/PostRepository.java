package com.example.RedditClone.repository;

import com.example.RedditClone.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT * FROM Post p WHERE p.postId = ?1", nativeQuery = true)
    Optional<List<Post>> findPostsByUserId(Long userId);
}
