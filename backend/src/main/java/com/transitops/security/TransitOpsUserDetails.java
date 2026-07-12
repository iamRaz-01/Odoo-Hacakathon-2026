package com.transitops.security;

import com.transitops.entity.UserEntity;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class TransitOpsUserDetails implements UserDetails {
    private final Long id;
    private final String email;
    private final String password;
    private final boolean active;
    private final String role;
    public TransitOpsUserDetails(Long id, String email, String password, boolean active, String role) { this.id=id; this.email=email; this.password=password; this.active=active; this.role=role; }
    public static TransitOpsUserDetails from(UserEntity user) { return new TransitOpsUserDetails(user.getId(), user.getEmail(), user.getPasswordHash(), user.isActive(), user.getRole().getName()); }
    public Long getId() { return id; }
    public String getRole() { return role; }
    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return List.of(new SimpleGrantedAuthority("ROLE_" + role)); }
    @Override public String getPassword() { return password; }
    @Override public String getUsername() { return email; }
    @Override public boolean isEnabled() { return active; }
}
