package com.ciazhar.postgres.repository;

import com.ciazhar.postgres.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
