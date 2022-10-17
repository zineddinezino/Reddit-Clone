package com.example.RedditClone.controller;

import com.example.RedditClone.dto.post.PostRequestDto;
import com.example.RedditClone.dto.post.PostResponseDto;
import com.example.RedditClone.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody PostRequestDto postRequestDto) {
        postService.createPost(postRequestDto);
    }

    @GetMapping
    public List<PostResponseDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public PostResponseDto getPostById(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @GetMapping("/bySubreddit/{id}")
    public List<PostResponseDto> getPostBySubredditId(@PathVariable Long id) {
        return postService.getPostUnderSubreddit(id);
    }

    @GetMapping("/byUsername/{name}")
    public List<PostResponseDto> getPostsByUserName(@PathVariable String userName) {
        return postService.getPostsUnderUserName(userName);
    }
}
