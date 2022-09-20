package com.example.RedditClone.controller;

import com.example.RedditClone.dto.post.PostRequestDto;
import com.example.RedditClone.dto.post.PostResponseDto;
import com.example.RedditClone.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostRequestDto postRequestDto){
        postService.createPost(postRequestDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts(){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPost(id));
    }

    @GetMapping("/bySubreddit/{id}")
    public ResponseEntity<List<PostResponseDto>> getPostBySubredditId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostUnderSubreddit(id));
    }

    @GetMapping("/byUsername/{name}")
    public ResponseEntity<List<PostResponseDto>> getPostsByUserName(@PathVariable String userName){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostsUnderUserName(userName));
    }
}
