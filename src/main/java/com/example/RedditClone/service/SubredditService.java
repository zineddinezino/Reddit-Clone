package com.example.RedditClone.service;

import com.example.RedditClone.dto.SubredditDto;
import com.example.RedditClone.model.Subreddit;
import com.example.RedditClone.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepository subredditRepository;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit subreddit = subredditRepository.save(mapSuredditToDto(subredditDto));
        subredditDto.setId(subreddit.getSubredditId());
        return subredditDto;
    }

    private Subreddit mapSuredditToDto(SubredditDto subredditDto) {
        return Subreddit.builder()
                .subredditName(subredditDto.getSubredditName())
                .subredditDescription(subredditDto.getSubredditDescription())
                .build();
    }
    @Transactional
    public List<SubredditDto> getAll() {
        return subredditRepository
                .findAll()
                .stream()
                .map(this::mapToDto)
                .collect(toList());

    }

    private SubredditDto mapToDto(Subreddit subreddit){
        return SubredditDto.builder()
                .id(subreddit.getSubredditId())
                .subredditName(subreddit.getSubredditName())
                .numberOfPosts(subreddit.getPosts().size())
                .build();
    }
}
