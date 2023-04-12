package com.company.redditclone.service;

import com.company.redditclone.exceptions.SpringRedditException;
import com.company.redditclone.model.RefreshToken;
import com.company.redditclone.repository.RefreshTokenRepository;
import com.company.redditclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public RefreshToken generateRefreshToken(String username) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setUsername(username);
        refreshToken.setCreatedDate(Instant.now());
        return refreshTokenRepository.save(refreshToken);
    }

    void validateRefreshToken(String token, String username) {
        refreshTokenRepository.findByTokenAndUsername(token, username)
                .orElseThrow(() -> new SpringRedditException("Invalid refresh Token"));
    }

    public void deleteRefreshToken(String token, String username) {
        refreshTokenRepository.deleteByTokenAndUsername(token, username);
    }
}
