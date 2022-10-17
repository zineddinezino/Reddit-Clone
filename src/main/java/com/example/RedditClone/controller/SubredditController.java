package com.example.RedditClone.controller;

import com.example.RedditClone.dto.Subreddit.SubredditDto;
import com.example.RedditClone.service.SubredditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
@Slf4j
public class SubredditController {

    private final SubredditService subredditService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubredditDto createSubreddit(@RequestBody SubredditDto subredditDto) {
        return subredditService.save(subredditDto);
    }

    @GetMapping
    public List<SubredditDto> getAllSubreddits(){
        return subredditService.getAll();
    }
}
