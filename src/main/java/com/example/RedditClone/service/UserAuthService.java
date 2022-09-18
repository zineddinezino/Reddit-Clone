package com.example.RedditClone.service;

import com.example.RedditClone.dto.AuthenticationResponse;
import com.example.RedditClone.dto.LoginRequestData;
import com.example.RedditClone.dto.RegisterRequestData;
import com.example.RedditClone.exception.RedditCloneException;
import com.example.RedditClone.model.EmailNotification;
import com.example.RedditClone.model.TokenVerification;
import com.example.RedditClone.model.User;
import com.example.RedditClone.repository.TokenVerificationRepository;
import com.example.RedditClone.repository.UserRepository;
import com.example.RedditClone.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserAuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final TokenVerificationRepository tokenVerificationRepository;

    private final MailService mailService;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

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
        mailService.sendEmail(new EmailNotification(
                "Please activate your account",
                user.getUserEmail(),
                "Hello "+user.getUserName()+",\n" +"Welcome to the Cloned version of Reddit!\n" +
                        "We thank you for your registration on our site and we are happy to share with you our reddits! \n"+
                        "To activate your account please click on the link below : "+
                        "http://localhost:8080/api/auth/accountActivation/"+ token
        ));

    }

    private String TokenVerificationGenerator(User user) {
        String token = UUID.randomUUID().toString();
        TokenVerification tokenVerification = new TokenVerification();
        tokenVerification.setToken(token);
        tokenVerification.setUser(user);
        tokenVerificationRepository.save(tokenVerification);
        return token;

    }

    public void tokenVerification(String token) {
        Optional<TokenVerification> verificationOptional = tokenVerificationRepository.findByToken(token);
        verificationOptional.orElseThrow(() -> new RedditCloneException("Invalid token"));

        // Get the user using the token and enable the account
        fetchUserEnable(verificationOptional.get());
    }

    @Transactional
    private void fetchUserEnable(TokenVerification tokenVerification) {
        String userName = tokenVerification.getUser().getUserName();
        User user = userRepository.findByUserName(userName).orElseThrow(() -> new RedditCloneException("User not found"));
        user.setAccountEnabled(true);
        userRepository.save(user);
    }

    public AuthenticationResponse login(LoginRequestData loginRequestData) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestData.getUsername(), loginRequestData.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(token, loginRequestData.getUsername());
    }
}
