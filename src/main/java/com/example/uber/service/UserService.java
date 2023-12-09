package com.example.uber.service;

import com.example.uber.model.request.DriverRegisterRequest;
import com.example.uber.model.request.LoginRequest;
import com.example.uber.model.request.PassengerRegisterRequest;
import com.example.uber.model.response.LoginResponse;

public interface UserService {
    void registerPassenger(PassengerRegisterRequest passengerRequest);
    void registerDriver(DriverRegisterRequest driverRequest);

    LoginResponse login(LoginRequest loginRequest);

    void registerAdmin(PassengerRegisterRequest adminRequest);
}
