package com.transitops.entity;

import jakarta.persistence.*;
import java.time.Instant;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "user", indexes = {@Index(name = "idx_user_role_id", columnList = "role_id"), @Index(name = "idx_user_active", columnList = "active")})
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "user_id") private Long id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "role_id", nullable = false) private RoleEntity role;
    @Column(name = "first_name", nullable = false, length = 100) private String firstName;
    @Column(name = "last_name", nullable = false, length = 100) private String lastName;
    @Column(nullable = false, unique = true, length = 255) private String email;
    @Column(name = "password_hash", nullable = false, length = 255) private String passwordHash;
    @Column(nullable = false) private boolean active = true;
    @CreationTimestamp @Column(name = "created_at", nullable = false, updatable = false) private Instant createdAt;
    @UpdateTimestamp @Column(name = "updated_at", nullable = false) private Instant updatedAt;
    public Long getId() { return id; } public RoleEntity getRole() { return role; } public void setRole(RoleEntity role) { this.role = role; }
    public String getFirstName() { return firstName; } public void setFirstName(String value) { firstName = value; } public String getLastName() { return lastName; } public void setLastName(String value) { lastName = value; }
    public String getEmail() { return email; } public void setEmail(String value) { email = value; } public String getPasswordHash() { return passwordHash; } public void setPasswordHash(String value) { passwordHash = value; }
    public boolean isActive() { return active; } public void setActive(boolean value) { active = value; } public Instant getCreatedAt() { return createdAt; } public Instant getUpdatedAt() { return updatedAt; }
}
