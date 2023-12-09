package com.example.uber.controller;

import com.example.uber.model.Car;
import com.example.uber.model.request.CarRequest;
import com.example.uber.model.response.CarResponse;
import com.example.uber.service.CarService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/car")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class CarController {
    private final CarService carService;
    private final ModelMapper modelMapper;

    @PostMapping("/add/{driverId}")
    public ResponseEntity<Car> addCarForDriver(@RequestBody CarRequest carRequest, @PathVariable UUID driverId) {
        Car savedCar = carService.addCarForDriver(carRequest, driverId);
        return ResponseEntity.ok(savedCar);
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<CarResponse> getCarForDriver(@PathVariable UUID driverId){
        Car car = carService.getCarByDriverId(driverId);
        return ResponseEntity.ok(modelMapper.map(car, CarResponse.class));
    }
}
