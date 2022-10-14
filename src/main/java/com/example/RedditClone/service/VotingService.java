package com.example.RedditClone.service;

import com.example.RedditClone.dto.voting.VotingDto;
import com.example.RedditClone.dto.voting.VotingMapper;
import com.example.RedditClone.exception.RedditCloneException;
import com.example.RedditClone.model.Post;
import com.example.RedditClone.model.Voting;
import com.example.RedditClone.repository.PostRepository;
import com.example.RedditClone.repository.VotingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.RedditClone.model.VotingType.UPVOTE;

@Service
@AllArgsConstructor
public class VotingService {

    private final PostRepository postRepository;
    private final VotingRepository votingRepository;
    private final UserAuthService userAuthService;
    private final VotingMapper votingMapper;

    public void voting(VotingDto votingDto){
        Post post = postRepository.findById(votingDto.getPostId()).orElseThrow(() -> new RedditCloneException("Post not found"));
        Optional<Voting> voteOnPostByUser = votingRepository.findTopByPostAndUserOrderByVotingIdDesc(post, userAuthService.getCurrentLoggedUser());
        if(voteOnPostByUser.isPresent() && voteOnPostByUser.get().getVotingType().equals(votingDto.getVotingType())){
            throw new RedditCloneException("You've already voted on this post");
        }

        if(UPVOTE.equals(votingDto.getVotingType())){
            post.setPostVotingCount(post.getPostVotingCount() + 1);
        }else{
            post.setPostVotingCount(post.getPostVotingCount() - 1);
        }

        votingRepository.save(votingMapper.toModel(votingDto, post, userAuthService.getCurrentLoggedUser()));
        postRepository.save(post);
    }
}
