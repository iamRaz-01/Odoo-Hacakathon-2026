package com.transitops.service;
import com.transitops.dto.*;
import java.util.List;
public interface UserService { List<UserResponse> findAll(); UserResponse findById(Long id); UserResponse create(RegisterUserRequest request); UserResponse update(Long id, UpdateUserRequest request); UserResponse setActive(Long id, boolean active); }
