package com.transitops.dto;
import java.time.Instant;
public record RoleResponse(Long id, String name, String description, Instant createdAt, Instant updatedAt) {}
