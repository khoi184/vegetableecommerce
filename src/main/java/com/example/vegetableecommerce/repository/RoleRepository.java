package com.example.vegetableecommerce.repository;

import com.example.vegetableecommerce.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String name);
}
