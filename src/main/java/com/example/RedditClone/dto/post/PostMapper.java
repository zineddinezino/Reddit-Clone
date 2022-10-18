package com.example.RedditClone.dto.post;

import com.example.RedditClone.model.Post;
import com.example.RedditClone.model.Subreddit;
import com.example.RedditClone.model.User;
import com.example.RedditClone.repository.CommentRepository;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PostMapper {
    @Autowired
    private CommentRepository commentRepository;

    @Mapping(target = "userName", source = "user.userName")
    @Mapping(target = "subredditName", source = "subreddit.subredditName")
    @Mapping(target = "commentCont", expression = "java(countComments(post))")
    @Mapping(target = "duration", expression = "java(String.valueOf(java.time.Instant.now()))") //getDuration must be used here
    public abstract PostResponseDto toDTO(Post post);


    @Mapping(target = "postCreatedDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "postVotingCount", constant = "0")
    public abstract Post toModel(PostRequestDto postRequestDto, Subreddit subreddit, User user);

    String getDuration(Post post){
        return TimeAgo.using(post.getPostCreatedDate().toEpochMilli());
    }
    Integer countComments(Post post){
        return commentRepository.findByPost(post.getPostId()).size();
    }


}
