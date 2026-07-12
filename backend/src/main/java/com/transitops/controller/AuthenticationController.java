package com.transitops.controller;

import com.transitops.dto.*;
import com.transitops.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/v1/auth") @Tag(name = "Authentication")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    public AuthenticationController(AuthenticationService authenticationService) { this.authenticationService = authenticationService; }
    @PostMapping("/login") public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) { return ResponseEntity.ok(ApiResponse.success("Login successful.", authenticationService.login(request))); }
    @PostMapping("/logout") public ResponseEntity<ApiResponse<Void>> logout() { authenticationService.logout(); return ResponseEntity.ok(ApiResponse.success("Logout successful.", null)); }
    @PostMapping("/refresh") public ResponseEntity<ApiResponse<JwtResponse>> refresh(@Valid @RequestBody RefreshTokenRequest request) { return ResponseEntity.ok(ApiResponse.success("Token refreshed successfully.", authenticationService.refresh(request))); }
    @GetMapping("/me") public ResponseEntity<ApiResponse<UserResponse>> me() { return ResponseEntity.ok(ApiResponse.success("Current user retrieved successfully.", authenticationService.currentUser())); }
}
