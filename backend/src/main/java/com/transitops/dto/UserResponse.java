package com.transitops.dto;
import java.time.Instant;
public record UserResponse(Long id, String firstName, String lastName, String email, boolean active, RoleResponse role, Instant createdAt, Instant updatedAt) {}
