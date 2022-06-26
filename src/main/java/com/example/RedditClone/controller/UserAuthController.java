package com.example.RedditClone.controller;

import com.example.RedditClone.dto.RegisterRequestData;
import com.example.RedditClone.service.UserAuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequestData registerRequestData){
        userAuthService.signup(registerRequestData);
        return new ResponseEntity<>("Your account have been created successfully", HttpStatus.OK);

    }
}
