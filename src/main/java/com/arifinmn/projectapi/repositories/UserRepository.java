package com.arifinmn.projectapi.repositories;

import com.arifinmn.projectapi.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Boolean existsByUsername(String username);
    Users findByUsername(String username);
}
