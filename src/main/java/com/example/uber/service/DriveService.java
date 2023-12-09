package com.example.uber.service;

import com.example.uber.model.Drive;
import com.example.uber.model.request.DriveRequest;
import com.example.uber.model.response.DriveResponse;

import java.util.List;
import java.util.UUID;

public interface DriveService {

    DriveResponse startDrive(UUID requestId, UUID driverId, DriveRequest driveRequest);

    Boolean finishDrive(UUID driveId, float kmTravelled);

    Drive getById(UUID driveId);

    DriveResponse getDriveByRequestId(UUID requestId);

    DriveResponse gradeDrive(UUID driveId, float grade);

    UUID payDrive(UUID driveId, float totalPriceToPay);

    List<Drive> getAllDrivesByDriverId(UUID driverId);
}
