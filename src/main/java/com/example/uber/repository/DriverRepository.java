package com.example.uber.repository;

import com.example.uber.model.Driver;
import com.example.uber.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DriverRepository extends JpaRepository<Driver, UUID> {
    Boolean existsByEmail(String email);

    Driver findByEmail(String email);

    List<Driver> findAllByIsApprovedAndIsDenied(Boolean isApproved, Boolean isDenied);

}
