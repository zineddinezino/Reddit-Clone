package com.example.RedditClone.repository;

import com.example.RedditClone.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit, Long> {

    @Query("SELECT s FROM Subreddit s WHERE s.subredditName = ?1")
    Optional<Subreddit> findByName(String subredditName);
}
