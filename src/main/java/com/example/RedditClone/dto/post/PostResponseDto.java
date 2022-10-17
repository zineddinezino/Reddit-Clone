package com.example.RedditClone.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponseDto {
    private Long postId;
    private String subredditName;
    private String userName;
    private String postName;
    private String postUrl;
    private String postDescription;
    private Integer commentCont;
    private Integer postVotingCount;
    private String duration;
}
