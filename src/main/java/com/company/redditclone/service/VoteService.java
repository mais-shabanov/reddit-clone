package com.company.redditclone.service;

import com.company.redditclone.dto.VoteDto;
import com.company.redditclone.exceptions.PostNotFoundException;
import com.company.redditclone.exceptions.SpringRedditException;
import com.company.redditclone.mapper.VoteMapper;
import com.company.redditclone.model.Post;
import com.company.redditclone.model.Vote;
import com.company.redditclone.repository.PostRepository;
import com.company.redditclone.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.company.redditclone.model.VoteType.UPVOTE;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;
    private final VoteMapper voteMapper;

    @Transactional
    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post not found with id - " + voteDto.getPostId()));
        Optional<Vote> voteByPostAndUser =
                voteRepository.findTopByPostAndUserOrderByVoteIdDesc(
                        post,
                        authService.getCurrentUser()
                );
        if (voteByPostAndUser.isPresent() && voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())) {
            throw new SpringRedditException("You have already " + voteDto.getVoteType() + "'d for this post");
        }
        if (UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }
        voteRepository.save(voteMapper.mapVoteToDto(voteDto, post, authService.getCurrentUser()));
        postRepository.save(post);
    }

}
