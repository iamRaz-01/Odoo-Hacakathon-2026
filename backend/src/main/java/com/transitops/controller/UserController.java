package com.transitops.controller;

import com.transitops.dto.*;
import com.transitops.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/v1/users") @Tag(name = "Users")
public class UserController {
    private final UserService users;
    public UserController(UserService users) { this.users = users; }
    @GetMapping @PreAuthorize("hasAnyRole('ADMIN','FLEET_MANAGER','SAFETY_OFFICER','FINANCE_OFFICER')") public ResponseEntity<ApiResponse<List<UserResponse>>> findAll() { return ResponseEntity.ok(ApiResponse.success("Users retrieved successfully.", users.findAll())); }
    @GetMapping("/{id}") @PreAuthorize("hasAnyRole('ADMIN','FLEET_MANAGER','SAFETY_OFFICER','FINANCE_OFFICER') or #id == authentication.principal.id") public ResponseEntity<ApiResponse<UserResponse>> findById(@PathVariable Long id) { return ResponseEntity.ok(ApiResponse.success("User retrieved successfully.", users.findById(id))); }
    @PostMapping @PreAuthorize("hasRole('ADMIN')") public ResponseEntity<ApiResponse<UserResponse>> create(@Valid @RequestBody RegisterUserRequest request) { return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("User created successfully.", users.create(request))); }
    @PutMapping("/{id}") @PreAuthorize("hasRole('ADMIN')") public ResponseEntity<ApiResponse<UserResponse>> update(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest request) { return ResponseEntity.ok(ApiResponse.success("User updated successfully.", users.update(id, request))); }
    @PatchMapping("/{id}/activate") @PreAuthorize("hasRole('ADMIN')") public ResponseEntity<ApiResponse<UserResponse>> activate(@PathVariable Long id) { return ResponseEntity.ok(ApiResponse.success("User activated successfully.", users.setActive(id, true))); }
    @PatchMapping("/{id}/deactivate") @PreAuthorize("hasRole('ADMIN')") public ResponseEntity<ApiResponse<UserResponse>> deactivate(@PathVariable Long id) { return ResponseEntity.ok(ApiResponse.success("User deactivated successfully.", users.setActive(id, false))); }
}
