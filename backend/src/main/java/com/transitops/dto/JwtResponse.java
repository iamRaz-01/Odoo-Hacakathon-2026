package com.transitops.dto;
public record JwtResponse(String accessToken, String refreshToken, String tokenType, long expiresIn) {}
