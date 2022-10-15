package com.example.RedditClone.service;

import com.example.RedditClone.exception.RedditCloneException;
import com.example.RedditClone.model.RefreshToken;
import com.example.RedditClone.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateRefreshToken(){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setRtCreatedDate(Instant.now());
        return refreshToken;
    }

    public void validateRefreshToken(String rToken){
        RefreshToken refreshToken = refreshTokenRepository.findByToken(rToken)
                .orElseThrow(() -> new RedditCloneException("Refresh Token not found"));
    }

    public void deleteRefreshToken(String token){
        refreshTokenRepository.deleteByToken(token);
    }
}
