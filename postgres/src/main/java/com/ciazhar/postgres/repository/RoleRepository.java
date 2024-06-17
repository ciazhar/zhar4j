package com.ciazhar.postgres.repository;

import com.ciazhar.postgres.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
