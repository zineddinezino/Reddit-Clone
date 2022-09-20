package com.example.RedditClone.service;

import com.example.RedditClone.dto.post.PostMapper;
import com.example.RedditClone.dto.post.PostRequestDto;
import com.example.RedditClone.dto.post.PostResponseDto;
import com.example.RedditClone.exception.RedditCloneException;
import com.example.RedditClone.model.Post;
import com.example.RedditClone.model.Subreddit;
import com.example.RedditClone.model.User;
import com.example.RedditClone.repository.PostRepository;
import com.example.RedditClone.repository.SubredditRepository;
import com.example.RedditClone.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final SubredditRepository subredditRepository;
    private final PostMapper postMapper;
    private final UserAuthService userAuthService;
    private final UserRepository userRepository;

    public void createPost(PostRequestDto postRequestDto){
        Subreddit subreddit = subredditRepository.findByName(postRequestDto.getSubredditName())
                .orElseThrow(() -> new RedditCloneException("Subreddit not found"));
        postRepository.save(postMapper.toModel(postRequestDto, subreddit, userAuthService.getCurrentLoggedUser()));

    }
    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long postId){
        return postMapper.toDTO(postRepository.findById(postId)
                .orElseThrow(() -> new RedditCloneException("Post not found")));
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getAllPosts(){
        return postRepository.findAll()
                .stream()
                .map(postMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getPostUnderSubreddit(Long subredditId){
        Subreddit subreddit = subredditRepository.findById(subredditId).orElseThrow(() -> new RedditCloneException("Subreddit not found"));
        return subreddit.getPosts()
                .stream()
                .map(postMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getPostsUnderUser(Long userId){
        List<Post> posts = postRepository.findPostsByUserId(userId)
                .orElseThrow(() -> new RedditCloneException("user posts not found"));
        return posts.stream()
                .map(postMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getPostsUnderUserName(String userName){
        User user = userRepository.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        List<Post> posts = postRepository.findPostsByUserId(user.getUserId())
                .orElseThrow(() -> new RedditCloneException("user posts not found"));
        return posts.stream()
                .map(postMapper::toDTO)
                .collect(Collectors.toList());
    }
}
