package com.transitops.service.impl;
import com.transitops.dto.*;
import com.transitops.entity.RoleEntity;
import com.transitops.exception.ConflictException;
import com.transitops.exception.ResourceNotFoundException;
import com.transitops.mapper.RoleMapper;
import com.transitops.repository.RoleRepository;
import com.transitops.repository.UserRepository;
import com.transitops.service.RoleService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roles; private final UserRepository users; private final RoleMapper mapper;
    public RoleServiceImpl(RoleRepository roles, UserRepository users, RoleMapper mapper) { this.roles = roles; this.users = users; this.mapper = mapper; }
    @Transactional(readOnly = true) public List<RoleResponse> findAll() { return roles.findAll().stream().map(mapper::toResponse).toList(); }
    @Transactional public RoleResponse create(RoleRequest request) { String name = request.name().trim().toUpperCase(); if (roles.existsByName(name)) throw new ConflictException("Role name already exists."); RoleEntity role = new RoleEntity(); role.setName(name); role.setDescription(request.description()); return mapper.toResponse(roles.save(role)); }
    @Transactional public RoleResponse update(Long id, RoleRequest request) { RoleEntity role = get(id); String name = request.name().trim().toUpperCase(); if (!role.getName().equals(name) && roles.existsByName(name)) throw new ConflictException("Role name already exists."); role.setName(name); role.setDescription(request.description()); return mapper.toResponse(role); }
    @Transactional public void delete(Long id) { RoleEntity role = get(id); if (users.findAll().stream().anyMatch(user -> user.getRole().getId().equals(id))) throw new ConflictException("A role assigned to users cannot be deleted."); roles.delete(role); }
    private RoleEntity get(Long id) { return roles.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role not found.")); }
}
