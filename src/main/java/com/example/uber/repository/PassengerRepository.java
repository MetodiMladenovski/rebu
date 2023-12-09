package com.example.uber.repository;

import com.example.uber.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PassengerRepository extends JpaRepository<Passenger, UUID> {
    Boolean existsByEmail(String email);

    Passenger findByEmail(String email);
}
