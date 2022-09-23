package com.example.RedditClone.repository;

import com.example.RedditClone.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit, Long> {

    //@Query(value = "SELECT * FROM Subreddit s WHERE s.subredditName = ?1", nativeQuery = true)
    Optional<Subreddit> findBySubredditName(String subredditName);
}
