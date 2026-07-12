package com.transitops.security;

import static org.junit.jupiter.api.Assertions.*;
import com.transitops.exception.UnauthorizedException;
import org.junit.jupiter.api.Test;

class JwtTokenProviderTest {
    private final JwtTokenProvider tokens = new JwtTokenProvider("MDEyMzQ1Njc4OWFiY2RlZjAxMjM0NTY3ODlhYmNkZWY=", 60_000, 120_000);
    @Test void createsAndParsesAccessToken() { var user = new TransitOpsUserDetails(7L, "admin@transitops.test", "", true, "ADMIN"); var parsed = tokens.parseAccessToken(tokens.createAccessToken(user)); assertEquals(7L, parsed.getId()); assertEquals("ADMIN", parsed.getRole()); }
    @Test void rejectsRefreshTokenAsAccessToken() { var user = new TransitOpsUserDetails(7L, "admin@transitops.test", "", true, "ADMIN"); assertThrows(UnauthorizedException.class, () -> tokens.parseAccessToken(tokens.createRefreshToken(user))); }
}
