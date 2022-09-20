package com.example.RedditClone.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {
    private Long postId;
    private String subredditName;
    private String userName;
    private String postName;
    private String postUrl;
    private String postDescription;
}
