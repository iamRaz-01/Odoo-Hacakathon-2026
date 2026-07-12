package com.transitops.security;

import com.transitops.exception.UnauthorizedException;
import com.transitops.service.JwtService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider implements JwtService {
    private final SecretKey key; private final long accessExpiration; private final long refreshExpiration;
    public JwtTokenProvider(@Value("${app.jwt.secret}") String secret, @Value("${app.jwt.access-token-expiration}") long accessExpiration, @Value("${app.jwt.refresh-token-expiration}") long refreshExpiration) {
        if (secret == null || secret.isBlank()) throw new IllegalStateException("JWT_SECRET environment variable is required.");
        byte[] keyBytes; try { keyBytes = Base64.getDecoder().decode(secret); } catch (IllegalArgumentException exception) { keyBytes = secret.getBytes(StandardCharsets.UTF_8); }
        if (keyBytes.length < 32) throw new IllegalStateException("JWT secret must be at least 32 bytes.");
        this.key = Keys.hmacShaKeyFor(keyBytes); this.accessExpiration = accessExpiration; this.refreshExpiration = refreshExpiration;
    }
    public String createAccessToken(TransitOpsUserDetails user) { return create(user, "access", accessExpiration); }
    public String createRefreshToken(TransitOpsUserDetails user) { return create(user, "refresh", refreshExpiration); }
    public TransitOpsUserDetails parseAccessToken(String token) { return parse(token, "access"); }
    public TransitOpsUserDetails parseRefreshToken(String token) { return parse(token, "refresh"); }
    public long getAccessTokenExpirationSeconds() { return accessExpiration / 1000; }
    private String create(TransitOpsUserDetails user, String type, long expiration) { Instant now = Instant.now(); return Jwts.builder().subject(user.getUsername()).claim("uid", user.getId()).claim("role", user.getRole()).claim("typ", type).issuedAt(java.util.Date.from(now)).expiration(java.util.Date.from(now.plusMillis(expiration))).signWith(key).compact(); }
    private TransitOpsUserDetails parse(String token, String requiredType) { try { Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload(); if (!requiredType.equals(claims.get("typ", String.class))) throw new UnauthorizedException("Invalid token type."); Number id = claims.get("uid", Number.class); String role = claims.get("role", String.class); if (id == null || role == null) throw new UnauthorizedException("Invalid token."); return new TransitOpsUserDetails(id.longValue(), claims.getSubject(), "", true, role); } catch (JwtException | IllegalArgumentException exception) { throw new UnauthorizedException("Invalid or expired token."); } }
}
