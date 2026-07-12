package com.transitops.mapper;
import com.transitops.dto.RoleResponse;
import com.transitops.entity.RoleEntity;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface RoleMapper { RoleResponse toResponse(RoleEntity entity); }
