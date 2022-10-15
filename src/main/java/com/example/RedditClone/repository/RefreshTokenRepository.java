package com.example.RedditClone.repository;

import com.example.RedditClone.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String rToken);

    void deleteByToken(String token);
}
