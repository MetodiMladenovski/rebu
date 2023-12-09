package com.example.uber.controller;

import com.example.uber.model.Driver;
import com.example.uber.model.response.DriverResponse;
import com.example.uber.service.DriverService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/driver")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class DriverController {

    private final DriverService driverService;

    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<DriverResponse>> findAllApprovedAndAvailableDrivers() {
        return ResponseEntity.ok(driverService.findAllApprovedAndAvailableDrivers().stream()
                .map(driver -> modelMapper.map(driver, DriverResponse.class)).collect(Collectors.toList()));
    }

    @GetMapping("/unapproved")
    public ResponseEntity<List<DriverResponse>> findAllUnapprovedDrivers() {
        return ResponseEntity.ok(driverService.findAllUnapprovedDrivers().stream()
                .map(driver -> modelMapper.map(driver, DriverResponse.class)).collect(Collectors.toList()));
    }

    @GetMapping("/{driverId}")
    public ResponseEntity<DriverResponse> findDriverById(@PathVariable UUID driverId) {
        Driver driver = driverService.getDriverById(driverId);
        DriverResponse driverResponse = modelMapper.map(driver, DriverResponse.class);
        return ResponseEntity.ok(driverResponse);
    }

    @PostMapping("/approve/{driverId}")
    public ResponseEntity<Boolean> approveDriverAccount(@PathVariable UUID driverId) {
        return ResponseEntity.ok(driverService.approveAccount(driverId));
    }

    @PostMapping("/deny/{driverId}")
    public ResponseEntity<Boolean> denyDriverAccount(@PathVariable UUID driverId) {
        return ResponseEntity.ok(driverService.denyAccount(driverId));
    }

    @PostMapping(path = "/{driverId}/profile/picture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DriverResponse> changeProfileImage(@PathVariable UUID driverId, @RequestPart("picture") MultipartFile picture) {
        return ResponseEntity.ok(driverService.changeProfilePicture(driverId, picture));
    }

    @GetMapping("/{driverId}/profile/picture")
    public ResponseEntity<Resource> getProfileImage(@PathVariable UUID driverId) {
        Resource resource = driverService.getProfilePicture(driverId);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "image/jpeg");
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
