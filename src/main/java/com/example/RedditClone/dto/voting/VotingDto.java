package com.example.RedditClone.dto.voting;

import com.example.RedditClone.model.VotingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotingDto {
    private Long postId;
    private VotingType votingType;
}
