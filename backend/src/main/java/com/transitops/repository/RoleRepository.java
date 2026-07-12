package com.transitops.repository;
import com.transitops.entity.RoleEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RoleRepository extends JpaRepository<RoleEntity, Long> { Optional<RoleEntity> findByName(String name); boolean existsByName(String name); }
