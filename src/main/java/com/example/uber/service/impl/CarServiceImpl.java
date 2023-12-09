package com.example.uber.service.impl;

import com.example.uber.exceptions.custom.ResourceNotFoundException;
import com.example.uber.model.Car;
import com.example.uber.model.Driver;
import com.example.uber.model.request.CarRequest;
import com.example.uber.repository.CarRepository;
import com.example.uber.service.CarService;
import com.example.uber.service.DriverService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Log4j2
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final DriverService driverService;

    @Override
    public Car addCarForDriver(CarRequest carRequest, UUID driverId) {
        Car car = modelMapper.map(carRequest, Car.class);
        Driver driver = driverService.getDriverById(driverId);
        Car carWithAddedDriver = car.withDriver(driver);
        return carRepository.save(carWithAddedDriver);
    }

    @Override
    public Car getCarByDriverId(UUID driverId) {
        return carRepository.findByDriverId(driverId)
                .orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Car for driver not found."));
    }

    @Override
    public Car getCarById(UUID carId) {
        return carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Car not found."));
    }
}
