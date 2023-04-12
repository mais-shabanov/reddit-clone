package com.company.redditclone.repository;

import com.company.redditclone.model.Post;
import com.company.redditclone.model.User;
import com.company.redditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
