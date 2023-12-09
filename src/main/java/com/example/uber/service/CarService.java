package com.example.uber.service;

import com.example.uber.model.Car;
import com.example.uber.model.request.CarRequest;

import java.util.UUID;

public interface CarService {
    Car addCarForDriver(CarRequest carRequest, UUID driverId);

    Car getCarByDriverId(UUID driverId);

    Car getCarById(UUID carId);
}
