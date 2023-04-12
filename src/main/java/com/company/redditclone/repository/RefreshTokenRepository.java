package com.company.redditclone.repository;

import com.company.redditclone.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);
    void deleteByTokenAndUsername(String token, String username);

    Optional<RefreshToken> findByTokenAndUsername(String token, String username);
}
