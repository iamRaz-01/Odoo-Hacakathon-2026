package com.transitops.dto;
import jakarta.validation.constraints.*;
public record RegisterUserRequest(@NotBlank @Size(max=100) String firstName, @NotBlank @Size(max=100) String lastName, @NotBlank @Email @Size(max=255) String email, @NotBlank @Size(min=12, max=128) String password, @NotNull Long roleId) {}
