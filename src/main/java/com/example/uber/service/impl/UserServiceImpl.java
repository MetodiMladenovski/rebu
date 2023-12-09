package com.example.uber.service.impl;

import com.example.uber.exceptions.custom.UserAlreadyExistsException;
import com.example.uber.model.Admin;
import com.example.uber.model.Driver;
import com.example.uber.model.Passenger;
import com.example.uber.model.enums.DriverLevel;
import com.example.uber.model.enums.DriverStatus;
import com.example.uber.model.request.DriverRegisterRequest;
import com.example.uber.model.request.LoginRequest;
import com.example.uber.model.request.PassengerRegisterRequest;
import com.example.uber.model.response.LoginResponse;
import com.example.uber.repository.AdminRepository;
import com.example.uber.repository.DriverRepository;
import com.example.uber.repository.PassengerRepository;
import com.example.uber.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final PassengerRepository passengerRepository;
    private final DriverRepository driverRepository;
    private final PasswordEncoder passwordEncoder;

    private final AdminRepository adminRepository;

    @Override
    public void registerPassenger(PassengerRegisterRequest passengerRequest) {
        this.checkIfUserExistsByEmail(passengerRequest.getEmail());
        Passenger passenger = modelMapper.map(passengerRequest, Passenger.class);
        Passenger passengerWithEncryptedPassword =
                passenger.withEncryptedPassword(this.encodePassword(passengerRequest.getPassword()));
        passengerRepository.save(passengerWithEncryptedPassword);
    }

    @Override
    public void registerDriver(DriverRegisterRequest driverRequest) {
        this.checkIfUserExistsByEmail(driverRequest.getEmail());
        Driver driver = modelMapper.map(driverRequest, Driver.class);
        Driver driverWithEncryptedPassword = driver.withLevel(DriverLevel.BEGINNER)
                .withStatus(DriverStatus.AVAILABLE)
                .withEncryptedPassword(this.encodePassword(driverRequest.getPassword()));
        driverRepository.save(driverWithEncryptedPassword);
    }

    private void checkIfUserExistsByEmail(String email) {
        if (passengerRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException(HttpStatus.ALREADY_REPORTED, "User email already exists.");
        }
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String typeOfLoggedUser = "false";
        String loggedUserId = "empty";
        if (driverRepository.existsByEmail(loginRequest.getEmail())) {
            Driver driver = driverRepository.findByEmail(loginRequest.getEmail());
            if (passwordEncoder.matches(loginRequest.getPassword(), driver.getEncryptedPassword())) {
                typeOfLoggedUser = "Driver";
                loggedUserId = driver.getId().toString();
            }
        } else if (passengerRepository.existsByEmail(loginRequest.getEmail())) {
            Passenger passenger = passengerRepository.findByEmail(loginRequest.getEmail());
            if (passwordEncoder.matches(loginRequest.getPassword(), passenger.getEncryptedPassword())) {
                typeOfLoggedUser = "Passenger";
                loggedUserId = passenger.getId().toString();
            }
        } else if (adminRepository.existsByEmail(loginRequest.getEmail())) {
            Admin admin = adminRepository.findByEmail(loginRequest.getEmail());
            if (passwordEncoder.matches(loginRequest.getPassword(), admin.getEncryptedPassword())) {
                typeOfLoggedUser = "Admin";
                loggedUserId = admin.getId().toString();
            }
        }
        return new LoginResponse(typeOfLoggedUser, loggedUserId);
    }

    @Override
    public void registerAdmin(PassengerRegisterRequest adminRequest) {
        Admin adminToSave = new Admin(adminRequest.getEmail(), adminRequest.getName(), adminRequest.getSurname());
        Admin adminWithEncryptedPassword = adminToSave.withEncryptedPassword(this.encodePassword(adminRequest.getPassword()));
        adminRepository.save(adminWithEncryptedPassword);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
