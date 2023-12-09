package com.example.uber.controller;

import com.example.uber.model.request.RequestDriveRequest;
import com.example.uber.model.response.RequestDriveResponse;
import com.example.uber.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/request")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class RequestController {
    private RequestService requestService;

    @PostMapping("/make/{passengerId}")
    public ResponseEntity<RequestDriveResponse> makeRequest(@PathVariable UUID passengerId,
                                                            @RequestBody RequestDriveRequest requestDriveRequest) {
        RequestDriveResponse response;
        if (requestDriveRequest.getChosenDriverId() != null) {
            UUID chosenDriverUuid = UUID.fromString(requestDriveRequest.getChosenDriverId());
            response = requestService.makeRequestForSpecificDriver(passengerId, chosenDriverUuid, requestDriveRequest);
        } else {
            response = requestService.makeRequestForAllDrivers(passengerId, requestDriveRequest);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/confirm/{driverId}/{requestId}")
    public ResponseEntity<RequestDriveResponse> confirmRequest(@PathVariable UUID driverId, @PathVariable UUID requestId) {
        RequestDriveResponse requestDriveResponse = requestService.confirmRequest(driverId, requestId);
        return ResponseEntity.ok(requestDriveResponse);
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<RequestDriveResponse>> getAllCreatedRequests(@PathVariable UUID driverId) {
        List<RequestDriveResponse> allCreatedRequests = requestService.getAllCreatedRequests(driverId);
        return ResponseEntity.ok(allCreatedRequests);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<RequestDriveResponse> getRequestById(@PathVariable UUID requestId) {
        return ResponseEntity.ok(requestService.getRequestDriveResponse(requestId));
    }
}
