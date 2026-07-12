package com.transitops.service.impl;
import com.transitops.dto.*;
import com.transitops.entity.*;
import com.transitops.exception.*;
import com.transitops.mapper.UserMapper;
import com.transitops.repository.*;
import com.transitops.service.UserService;
import com.transitops.validator.EmailValidator;
import com.transitops.validator.PasswordValidator;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository users; private final RoleRepository roles; private final UserMapper mapper; private final PasswordEncoder encoder; private final EmailValidator emailValidator; private final PasswordValidator passwordValidator;
    public UserServiceImpl(UserRepository users, RoleRepository roles, UserMapper mapper, PasswordEncoder encoder, EmailValidator emailValidator, PasswordValidator passwordValidator) { this.users = users; this.roles = roles; this.mapper = mapper; this.encoder = encoder; this.emailValidator = emailValidator; this.passwordValidator = passwordValidator; }
    @Transactional(readOnly = true) public List<UserResponse> findAll() { return users.findAll().stream().map(mapper::toResponse).toList(); }
    @Transactional(readOnly = true) public UserResponse findById(Long id) { return mapper.toResponse(get(id)); }
    @Transactional public UserResponse create(RegisterUserRequest request) { String email = emailValidator.normalize(request.email()); if (users.existsByEmailIgnoreCase(email)) throw new DuplicateEmailException(email); passwordValidator.validate(request.password()); UserEntity user = new UserEntity(); user.setFirstName(request.firstName().trim()); user.setLastName(request.lastName().trim()); user.setEmail(email); user.setPasswordHash(encoder.encode(request.password())); user.setRole(getRole(request.roleId())); return mapper.toResponse(users.save(user)); }
    @Transactional public UserResponse update(Long id, UpdateUserRequest request) { UserEntity user = get(id); String email = emailValidator.normalize(request.email()); if (!user.getEmail().equals(email) && users.existsByEmailIgnoreCase(email)) throw new DuplicateEmailException(email); user.setFirstName(request.firstName().trim()); user.setLastName(request.lastName().trim()); user.setEmail(email); user.setRole(getRole(request.roleId())); if (request.password() != null && !request.password().isBlank()) { passwordValidator.validate(request.password()); user.setPasswordHash(encoder.encode(request.password())); } return mapper.toResponse(user); }
    @Transactional public UserResponse setActive(Long id, boolean active) { UserEntity user = get(id); user.setActive(active); return mapper.toResponse(user); }
    private UserEntity get(Long id) { return users.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found.")); }
    private RoleEntity getRole(Long id) { return roles.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role not found.")); }
}
