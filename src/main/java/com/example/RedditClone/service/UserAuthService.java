package com.example.RedditClone.service;

import com.example.RedditClone.dto.RegisterRequestData;
import com.example.RedditClone.model.TokenVerification;
import com.example.RedditClone.model.User;
import com.example.RedditClone.repository.TokenVerificationRepository;
import com.example.RedditClone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserAuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final TokenVerificationRepository tokenVerificationRepository;

    @Transactional
    public void signup(RegisterRequestData registerRequestData){
        User user = new User();
        user.setUserName(registerRequestData.getUsername());
        user.setUserEmail(registerRequestData.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestData.getPassword()));
        user.setUserCreatedDate(Instant.now());
        user.setAccountEnabled(false);

        // saving the user to the database
        userRepository.save(user);

        // the token generation & sending the token by email
        String token = TokenVerificationGenerator(user);

    }

    private String TokenVerificationGenerator(User user) {
        String token = UUID.randomUUID().toString();
        TokenVerification tokenVerification = new TokenVerification();
        tokenVerification.setToken(token);
        tokenVerification.setUser(user);
        tokenVerificationRepository.save(tokenVerification);
        return token;

    }
}
