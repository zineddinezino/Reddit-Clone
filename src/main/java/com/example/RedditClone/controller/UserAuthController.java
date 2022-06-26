package com.example.RedditClone.controller;

import com.example.RedditClone.dto.RegisterRequestData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserAuthController {

    @PostMapping("/signup")
    public void signup(@RequestBody RegisterRequestData registerRequestData){

    }
}
