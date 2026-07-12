package com.transitops.service;
import com.transitops.security.TransitOpsUserDetails;
public interface JwtService { String createAccessToken(TransitOpsUserDetails user); String createRefreshToken(TransitOpsUserDetails user); TransitOpsUserDetails parseAccessToken(String token); TransitOpsUserDetails parseRefreshToken(String token); long getAccessTokenExpirationSeconds(); }
