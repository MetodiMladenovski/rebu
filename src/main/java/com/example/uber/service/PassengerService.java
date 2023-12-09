package com.example.uber.service;

import com.example.uber.model.Passenger;

import java.util.UUID;

public interface PassengerService {
    Passenger getById(UUID passengerId);
}
