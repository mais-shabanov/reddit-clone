package com.company.redditclone.service;

import com.company.redditclone.dto.SubredditDto;
import com.company.redditclone.exceptions.SpringRedditException;
import com.company.redditclone.mapper.SubredditMapper;
import com.company.redditclone.model.Subreddit;
import com.company.redditclone.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;
    private final AuthService authService;

    @Transactional
    public void save(SubredditDto subredditDto) {
        Subreddit subreddit = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository
                .findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(toList());
    }

    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with ID - " + id));
        return subredditMapper.mapSubredditToDto(subreddit);
    }
}
