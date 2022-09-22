package com.example.RedditClone.dto.comment;

import com.example.RedditClone.model.Comment;
import com.example.RedditClone.model.Post;
import com.example.RedditClone.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId()")
    @Mapping(target = "userName", expression = "java(comment.getUser().getUserName()")
    CommentDto toDTO(Comment comment);

    @Mapping(target = "commentCreatedDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "commentText", expression = "java(commentDto.commentText)")
    @Mapping(target = "commentId", ignore = true)
    Comment toModel(CommentDto commentDto, Post post, User user);
}
