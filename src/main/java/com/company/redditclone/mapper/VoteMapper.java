package com.company.redditclone.mapper;

import com.company.redditclone.dto.VoteDto;
import com.company.redditclone.model.Post;
import com.company.redditclone.model.User;
import com.company.redditclone.model.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VoteMapper {

    @Mapping(target = "voteType", source = "voteDto.voteType")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    Vote mapVoteToDto(VoteDto voteDto, Post post, User user);
}
