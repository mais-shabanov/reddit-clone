package com.company.redditclone.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

import static io.jsonwebtoken.Jwts.parser;

@Service
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;

    @Value("${jwt.signing.key}")
    private String signingKey;

    public String generateToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return generateTokenWithUsername(principal.getUsername());
    }

    public boolean validateToken(String jwtToken) {
        parser().setSigningKey(signingKey).parseClaimsJws(jwtToken);
        return true;
    }

    public String getUsernameFromJwt(String token) {
        Claims claims = parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public String generateTokenWithUsername(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }

    public long getJwtExpirationInMillis() {
        return jwtExpirationInMillis;
    }
}
