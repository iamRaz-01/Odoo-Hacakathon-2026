package com.transitops.service.impl;

import com.transitops.dto.*;
import com.transitops.exception.*;
import com.transitops.mapper.UserMapper;
import com.transitops.repository.UserRepository;
import com.transitops.security.TransitOpsUserDetails;
import com.transitops.service.*;
import com.transitops.validator.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    private final AuthenticationManager authenticationManager; private final JwtService jwtService; private final UserRepository users; private final UserMapper mapper; private final EmailValidator emailValidator;
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService, UserRepository users, UserMapper mapper, EmailValidator emailValidator) { this.authenticationManager = authenticationManager; this.jwtService = jwtService; this.users = users; this.mapper = mapper; this.emailValidator = emailValidator; }
    @Override public LoginResponse login(LoginRequest request) { String email = emailValidator.normalize(request.email()); try { Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, request.password())); TransitOpsUserDetails user = (TransitOpsUserDetails) authentication.getPrincipal(); log.info("Successful login for user {}", user.getId()); var persisted = users.findById(user.getId()).orElseThrow(InvalidCredentialsException::new); return new LoginResponse(jwtService.createAccessToken(user), jwtService.createRefreshToken(user), "Bearer", jwtService.getAccessTokenExpirationSeconds(), mapper.toResponse(persisted)); } catch (AuthenticationException exception) { log.warn("Failed login attempt for email {}", email); throw new InvalidCredentialsException(); } }
    @Override public JwtResponse refresh(RefreshTokenRequest request) { TransitOpsUserDetails user = jwtService.parseRefreshToken(request.refreshToken()); var persisted = users.findById(user.getId()).orElseThrow(() -> new UnauthorizedException("User no longer exists.")); if (!persisted.isActive()) throw new UnauthorizedException("User account is inactive."); TransitOpsUserDetails current = TransitOpsUserDetails.from(persisted); return new JwtResponse(jwtService.createAccessToken(current), jwtService.createRefreshToken(current), "Bearer", jwtService.getAccessTokenExpirationSeconds()); }
    @Override public UserResponse currentUser() { Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); if (!(principal instanceof TransitOpsUserDetails user)) throw new UnauthorizedException("Authentication is required."); return mapper.toResponse(users.findById(user.getId()).orElseThrow(() -> new UnauthorizedException("User no longer exists."))); }
    @Override public void logout() { log.info("User {} logged out", currentUser().id()); }
}
