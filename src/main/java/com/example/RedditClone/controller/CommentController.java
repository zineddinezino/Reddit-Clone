package com.example.RedditClone.controller;

import com.example.RedditClone.dto.comment.CommentDto;
import com.example.RedditClone.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/comments/")
public class CommentController {

    private CommentService commentService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createComment(@RequestBody CommentDto commentDto){
        commentService.createComment(commentDto);
    }
    @GetMapping("postId/{postId}")
    public List<CommentDto> getCommentsByPost(@PathVariable Long postId){
        return commentService.getCommentsByPostId(postId);
    }
    @GetMapping("userName/{userName}")
    public List<CommentDto> getCommentsByUserName(@PathVariable String userName){
        return commentService.getCommentsByUserName(userName);
    }
}
