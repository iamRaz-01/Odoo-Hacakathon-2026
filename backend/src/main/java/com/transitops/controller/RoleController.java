package com.transitops.controller;

import com.transitops.dto.*;
import com.transitops.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/v1/roles") @Tag(name = "Roles") @PreAuthorize("hasRole('ADMIN')")
public class RoleController {
    private final RoleService roles;
    public RoleController(RoleService roles) { this.roles = roles; }
    @GetMapping public ResponseEntity<ApiResponse<List<RoleResponse>>> findAll() { return ResponseEntity.ok(ApiResponse.success("Roles retrieved successfully.", roles.findAll())); }
    @PostMapping public ResponseEntity<ApiResponse<RoleResponse>> create(@Valid @RequestBody RoleRequest request) { return ResponseEntity.status(201).body(ApiResponse.success("Role created successfully.", roles.create(request))); }
    @PutMapping("/{id}") public ResponseEntity<ApiResponse<RoleResponse>> update(@PathVariable Long id, @Valid @RequestBody RoleRequest request) { return ResponseEntity.ok(ApiResponse.success("Role updated successfully.", roles.update(id, request))); }
    @DeleteMapping("/{id}") public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) { roles.delete(id); return ResponseEntity.ok(ApiResponse.success("Role deleted successfully.", null)); }
}
