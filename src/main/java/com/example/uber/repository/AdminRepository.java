package com.example.uber.repository;

import com.example.uber.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
    Boolean existsByEmail(String email);

    Admin findByEmail(String email);
}
