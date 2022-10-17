package com.example.RedditClone.controller;

import com.example.RedditClone.dto.voting.VotingDto;
import com.example.RedditClone.service.VotingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/votes")
public class VotingController {

    private final VotingService votingService;

    public void vote(VotingDto votingDto){
        votingService.voting(votingDto);
    }
}
