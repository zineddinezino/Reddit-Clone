package com.example.RedditClone.controller;

import com.example.RedditClone.dto.AuthenticationResponse;
import com.example.RedditClone.dto.LoginRequestData;
import com.example.RedditClone.dto.RefreshTokenRequest;
import com.example.RedditClone.dto.RegisterRequestData;
import com.example.RedditClone.service.RefreshTokenService;
import com.example.RedditClone.service.UserAuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequestData registerRequestData){
        userAuthService.signup(registerRequestData);
        return new ResponseEntity<>("Your account have been created successfully", HttpStatus.OK);

    }

    @GetMapping("/accountActivation/{token}")
    public ResponseEntity<String> tokenVerification(@PathVariable String token) {
        userAuthService.tokenVerification(token);
        return new ResponseEntity<>("Your Account have been activated", HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequestData loginRequestData) {
        return userAuthService.login(loginRequestData);
    }

    @PostMapping("/refreshToken")
    public AuthenticationResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return userAuthService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody RefreshTokenRequest refreshTokenRequest){
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body("You are successfully logged out");

    }
}
