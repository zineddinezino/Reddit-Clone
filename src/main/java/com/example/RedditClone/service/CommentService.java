package com.example.RedditClone.service;

import com.example.RedditClone.dto.comment.CommentDto;
import com.example.RedditClone.dto.comment.CommentMapper;
import com.example.RedditClone.exception.RedditCloneException;
import com.example.RedditClone.model.EmailNotification;
import com.example.RedditClone.model.Post;
import com.example.RedditClone.model.User;
import com.example.RedditClone.repository.CommentRepository;
import com.example.RedditClone.repository.PostRepository;
import com.example.RedditClone.repository.UserRepository;
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
    private UserRepository userRepository;
    private MailContentBuilder mailContentBuilder;
    private MailService mailService;

    public void createComment(CommentDto commentDto){
        Post post = postRepository.findById(commentDto.getPostId()).orElseThrow(() -> new RedditCloneException("Post not found"));
        commentRepository.save(commentMapper.toModel(commentDto, post, userAuthService.getCurrentLoggedUser()));
        // Sending a notification
        // creating the body of the notification
        String message = mailContentBuilder.build(userAuthService.getCurrentLoggedUser() +" has commented in your post");
        mailService.sendEmail(new EmailNotification(
                "New comment in your post",
                post.getUser().getUserEmail(),
                message
        ));


    }

    public List<CommentDto> getCommentsByPostId(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(() -> new RedditCloneException("Post not found"));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<CommentDto> getCommentsByUserName(String userName){
        User user = userRepository.findByUserName(userName).orElseThrow(() -> new RedditCloneException("User not found"));
        return commentRepository.findCommentByUser(user)
                .stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }
}
