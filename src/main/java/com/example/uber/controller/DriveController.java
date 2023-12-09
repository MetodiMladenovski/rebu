package com.example.uber.controller;

import com.example.uber.model.request.DriveRequest;
import com.example.uber.model.response.DriveResponse;
import com.example.uber.service.DriveService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/drive")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class DriveController {

    private final DriveService driveService;

    @PostMapping("/start/{requestId}/{driverId}")
    public ResponseEntity<DriveResponse> startDrive(@PathVariable UUID requestId, @PathVariable UUID driverId, @RequestBody DriveRequest driveRequest) {
        return ResponseEntity.ok(driveService.startDrive(requestId, driverId, driveRequest));
    }

    @PostMapping("/finish/{driveId}")
    public ResponseEntity<Boolean> finishDrive(@PathVariable UUID driveId, @RequestParam float kmTravelled) {
        return ResponseEntity.ok(driveService.finishDrive(driveId, kmTravelled));
    }

    @GetMapping("/request/{requestId}")
    public ResponseEntity<DriveResponse> getDriveByRequestId(@PathVariable UUID requestId) {
        return ResponseEntity.ok(driveService.getDriveByRequestId(requestId));
    }

    @PostMapping("/grade/{driveId}")
    public ResponseEntity<DriveResponse> gradeDrive(@PathVariable UUID driveId, @RequestParam float gradeNum) {
        DriveResponse driveResponse = driveService.gradeDrive(driveId, gradeNum);
        return ResponseEntity.ok(driveResponse);
    }
    @PostMapping("/pay/{driveId}")
    public ResponseEntity<UUID> payDrive(@PathVariable UUID driveId, @RequestParam float totalPriceToPay) {
        UUID driveResponse = driveService.payDrive(driveId, totalPriceToPay);
        return ResponseEntity.ok(driveResponse);
    }
}
