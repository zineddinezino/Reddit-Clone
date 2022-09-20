package com.example.RedditClone.dto.post;

import com.example.RedditClone.model.Post;
import com.example.RedditClone.model.Subreddit;
import com.example.RedditClone.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {


    @Mapping(target = "userName", source = "user.userName")
    @Mapping(target = "subredditName", source = "subreddit.subredditName")
    PostResponseDto toDTO(Post post);


    @Mapping(target = "postCreatedDate", expression = "java(java.time.Instant.now())")
    Post toModel(PostRequestDto postRequestDto, Subreddit subreddit, User user);
}
