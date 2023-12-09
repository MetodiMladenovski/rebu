package com.example.uber.repository;

import com.example.uber.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
    Optional<Car> findByDriverId(UUID driverId);
}
