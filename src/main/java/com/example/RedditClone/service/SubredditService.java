package com.example.RedditClone.service;

import com.example.RedditClone.dto.Subreddit.SubredditDto;
import com.example.RedditClone.dto.Subreddit.SubredditMapper;
import com.example.RedditClone.exception.RedditCloneException;
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
    private final SubredditMapper subredditMapper;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit subreddit = subredditRepository.save(subredditMapper.toModel(subredditDto));
        subredditDto.setId(subreddit.getSubredditId());
        return subredditDto;
    }


    @Transactional
    public List<SubredditDto> getAll() {
        return subredditRepository
                .findAll()
                .stream()
                .map(subredditMapper::toDTO)
                .collect(toList());

    }

    public SubredditDto getSubreddit(Long id){
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new RedditCloneException("Subreddit not found"));
        return subredditMapper.toDTO(subreddit);
    }
}
