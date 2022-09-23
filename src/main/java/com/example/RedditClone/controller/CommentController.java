package com.example.RedditClone.controller;

import com.example.RedditClone.dto.comment.CommentDto;
import com.example.RedditClone.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/comments/")
public class CommentController {

    private CommentService commentService;
    @PostMapping
    public ResponseEntity createComment(@RequestBody CommentDto commentDto){
        commentService.createComment(commentDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @GetMapping("postId/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPost(@PathVariable Long postId){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentsByPostId(postId));
    }
    @GetMapping("userName/{userName}")
    public ResponseEntity<List<CommentDto>> getCommentsByUserName(@PathVariable String userName){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentsByUserName(userName));
    }
}
