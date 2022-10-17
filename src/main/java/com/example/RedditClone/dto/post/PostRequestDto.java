package com.example.RedditClone.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRequestDto {
    private Long postId;
    private String subredditName;
    private String postName;
    private String postUrl;
    private String postDescription;
}

