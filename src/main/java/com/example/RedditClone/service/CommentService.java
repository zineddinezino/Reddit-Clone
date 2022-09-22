package com.example.RedditClone.service;

import com.example.RedditClone.dto.comment.CommentDto;
import com.example.RedditClone.dto.comment.CommentMapper;
import com.example.RedditClone.exception.RedditCloneException;
import com.example.RedditClone.model.Post;
import com.example.RedditClone.repository.CommentRepository;
import com.example.RedditClone.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CommentService {

    private CommentMapper commentMapper;
    private PostRepository postRepository;
    private UserAuthService userAuthService;
    private CommentRepository commentRepository;

    public void createComment(CommentDto commentDto){
        Post post = postRepository.findById(commentDto.getPostId()).orElseThrow(() -> new RedditCloneException("Post not found"));
        commentRepository.save(commentMapper.toModel(commentDto, post, userAuthService.getCurrentLoggedUser()));
    }

    public List<CommentDto> getCommentsByPostId(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(() -> new RedditCloneException("Post not found"));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }
}
