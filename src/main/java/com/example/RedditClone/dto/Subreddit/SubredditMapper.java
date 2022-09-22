package com.example.RedditClone.dto.Subreddit;


import com.example.RedditClone.model.Post;
import com.example.RedditClone.model.Subreddit;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubredditMapper {
    @Mapping(target = "numberOfPosts", expression = "java(getNumberOfPosts(subreddit.getPosts()))")
    SubredditDto toDTO(Subreddit subreddit);


    default Integer getNumberOfPosts(List<Post> posts){
        return posts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subreddit toModel(SubredditDto subredditDto);
}
