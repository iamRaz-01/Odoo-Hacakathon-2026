package com.transitops.mapper;
import com.transitops.dto.UserResponse;
import com.transitops.entity.UserEntity;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper { UserResponse toResponse(UserEntity entity); }
