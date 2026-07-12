package com.transitops.service;
import com.transitops.dto.*;
public interface AuthenticationService { LoginResponse login(LoginRequest request); JwtResponse refresh(RefreshTokenRequest request); UserResponse currentUser(); void logout(); }
