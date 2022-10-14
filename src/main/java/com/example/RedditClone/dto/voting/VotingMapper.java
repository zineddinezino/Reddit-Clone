package com.example.RedditClone.dto.voting;

import com.example.RedditClone.model.Post;
import com.example.RedditClone.model.User;
import com.example.RedditClone.model.Voting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VotingMapper {

    @Mapping(target = "votingType", expression = "java(votingDto.getVotingType())")
    Voting toModel(VotingDto votingDto, Post post, User user);
}
