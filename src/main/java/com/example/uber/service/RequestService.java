package com.example.uber.service;

import com.example.uber.model.Request;
import com.example.uber.model.enums.RequestStatus;
import com.example.uber.model.request.RequestDriveRequest;
import com.example.uber.model.response.RequestDriveResponse;

import java.util.List;
import java.util.UUID;

public interface RequestService {
    RequestDriveResponse makeRequestForAllDrivers(UUID passengerId, RequestDriveRequest request);

    RequestDriveResponse makeRequestForSpecificDriver(UUID passengerId, UUID chosenDriverId, RequestDriveRequest request);

    List<RequestDriveResponse> getAllCreatedRequests(UUID driverId);

    RequestDriveResponse confirmRequest(UUID driverId, UUID requestToConfirmId);

    Request getById(UUID requestId);

    RequestDriveResponse getRequestDriveResponse(UUID requestId);

    void updateStatus(UUID requestId, RequestStatus status);
}
