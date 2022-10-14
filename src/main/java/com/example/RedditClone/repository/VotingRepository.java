package com.example.RedditClone.repository;

import com.example.RedditClone.model.Post;
import com.example.RedditClone.model.User;
import com.example.RedditClone.model.Voting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotingRepository extends JpaRepository<Voting, Long> {
    Optional<Voting> findTopByPostAndUserOrderByVotingIdDesc(Post post, User currentLoggedUser);
}
