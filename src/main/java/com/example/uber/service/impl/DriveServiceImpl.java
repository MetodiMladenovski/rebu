package com.example.uber.service.impl;

import com.example.uber.exceptions.custom.DriveAlreadyExistsException;
import com.example.uber.exceptions.custom.ResourceNotFoundException;
import com.example.uber.model.*;
import com.example.uber.model.enums.DriveStatus;
import com.example.uber.model.enums.DriverStatus;
import com.example.uber.model.enums.RequestStatus;
import com.example.uber.model.request.DriveRequest;
import com.example.uber.model.response.DriveResponse;
import com.example.uber.model.response.PaymentResponse;
import com.example.uber.repository.DriveRepository;
import com.example.uber.service.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DriveServiceImpl implements DriveService {

    private final CarService carService;
    private final DriverService driverService;
    private final RequestService requestService;
    private final PaymentService paymentService;
    private final DriveRepository driveRepository;
    private final ModelMapper modelMapper;

    @Override
    public DriveResponse startDrive(UUID requestId, UUID driverId, DriveRequest driveRequest) {
        Car car = carService.getCarByDriverId(driverId);
        Driver driver = driverService.getDriverById(driverId);
        Request request = requestService.getById(requestId);
        this.checkIfDriveExistsForRequest(requestId);
        Drive drive = new Drive(car, driver, request, driveRequest.getDestinationLatitude(), driveRequest.getDestinationLongitude());
        driveRepository.save(drive);
        return modelMapper.map(drive, DriveResponse.class);
    }

    private void checkIfDriveExistsForRequest(UUID requestId) {
        if (driveRepository.existsByRequestId(requestId)) {
            throw new DriveAlreadyExistsException(HttpStatus.CONFLICT, "Drive already exists for that request.");
        }
    }

    @Override
    public Boolean finishDrive(UUID driveId, float kmTravelled) {
        Drive drive = getById(driveId);
        requestService.updateStatus(drive.getRequest().getId(), RequestStatus.FINISHED);
        driverService.changeStatusForDriver(drive.getDriver().getId(), DriverStatus.AVAILABLE);
        driveRepository.save(drive.withStatus(DriveStatus.FINISHED).withKmTravelled(kmTravelled));
        return true;
    }

    @Override
    public Drive getById(UUID driveId) {
        return driveRepository.findById(driveId)
                .orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Drive not found."));
    }

    @Override
    public DriveResponse getDriveByRequestId(UUID requestId) {
        Drive drive = driveRepository.findByRequestId(requestId).orElseThrow(() ->
                new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Drive not found for that request."));
        return modelMapper.map(drive, DriveResponse.class);
    }

    @Override
    public List<Drive> getAllDrivesByDriverId(UUID driverId) {
        return driveRepository.findAllByDriverId(driverId);
    }

    @Override
    public DriveResponse gradeDrive(UUID driveId, float grade) {
        Drive drive = getById(driveId);
        Drive gradedDrive = drive.withGrade(grade);
        List<Drive> drivesForDriver = getAllDrivesByDriverId(drive.getDriver().getId())
                .stream()
                .filter(drive1 -> drive1.getGrade() != 0.0)
                .collect(Collectors.toList());
        driverService.updateGradeForDriver(gradedDrive.getDriver().getId(), grade, drivesForDriver);
        driveRepository.save(gradedDrive);
        return modelMapper.map(gradedDrive, DriveResponse.class);
    }

    @Override
    public UUID payDrive(UUID driveId, float totalPriceToPay) {
        Drive drive = getById(driveId);
        Request request = drive.getRequest();
        Passenger passenger = request.getPassenger();
        driveRepository.save(drive.withStatus(DriveStatus.PAYED));
        PaymentResponse savedPayment = paymentService.addPayment(drive, passenger, totalPriceToPay);
        return savedPayment.getId();
    }
}
