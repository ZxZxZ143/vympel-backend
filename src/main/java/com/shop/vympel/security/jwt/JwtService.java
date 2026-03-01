package com.shop.vympel.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class JwtService {

    private final SecretKey key;
    private final long accessTtlMin;
    private final long refreshTtlDays;

    public JwtService(String secret, long accessTtlMin, long refreshTtlDays) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessTtlMin = accessTtlMin;
        this.refreshTtlDays = refreshTtlDays;
    }

    public String generateAccessToken(String subject, List<String> roles) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(accessTtlMin * 60);
        return Jwts.builder()
                .subject(subject)
                .issuedAt(Date.from(now))
                .expiration(Date.from(exp))
                .claim("roles", roles)
                .claim("type", "access")
                .signWith(key)
                .compact();
    }

    public String generateRefreshToken(String subject) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(refreshTtlDays * 24 * 60 * 60);
        return Jwts.builder()
                .subject(subject)
                .issuedAt(Date.from(now))
                .expiration(Date.from(exp))
                .claims(Map.of("type", "refresh"))
                .signWith(key)
                .compact();
    }

    public JwtClaims parseAndValidate(String token) {
        var claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        String subject = claims.getSubject();
        List<String> role = claims.get("roles", List.class);
        String type = claims.get("type", String.class);

        return new JwtClaims(subject, role, type);
    }

    public record JwtClaims(String subject, List<String> role, String type) {}
}
