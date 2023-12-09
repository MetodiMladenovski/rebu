package com.example.uber.controller;


import com.example.uber.model.request.DriverRegisterRequest;
import com.example.uber.model.request.LoginRequest;
import com.example.uber.model.request.PassengerRegisterRequest;
import com.example.uber.model.response.LoginResponse;
import com.example.uber.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@AllArgsConstructor
@CrossOrigin(value = "http://localhost:3000/",methods = {RequestMethod.GET, RequestMethod.POST})
public class UserController {

    private final UserService userService;

    @PostMapping("/register/passenger")
    public ResponseEntity<Boolean> registerPassenger(@RequestBody PassengerRegisterRequest passenger) {
        userService.registerPassenger(passenger);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/register/driver")
    public ResponseEntity<Boolean> registerDriver(@RequestBody DriverRegisterRequest driver) {
        userService.registerDriver(driver);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<Boolean> registerAdmin(@RequestBody PassengerRegisterRequest admin) {
        userService.registerAdmin(admin);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }
}
