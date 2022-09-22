package com.example.RedditClone.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long commentId;
    private String commentText;
    private Long postId;
    private Instant commentCreatedDate;
    private String userName;
}
