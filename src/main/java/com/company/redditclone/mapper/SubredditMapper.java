package com.company.redditclone.mapper;

import com.company.redditclone.dto.SubredditDto;
import com.company.redditclone.model.Post;
import com.company.redditclone.model.Subreddit;
import com.company.redditclone.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubredditMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
    SubredditDto mapSubredditToDto(Subreddit subreddit);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    Subreddit mapDtoToSubreddit(SubredditDto subredditDto, User user);
}
