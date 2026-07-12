package com.transitops.service;
import com.transitops.dto.RoleRequest;
import com.transitops.dto.RoleResponse;
import java.util.List;
public interface RoleService { List<RoleResponse> findAll(); RoleResponse create(RoleRequest request); RoleResponse update(Long id, RoleRequest request); void delete(Long id); }
