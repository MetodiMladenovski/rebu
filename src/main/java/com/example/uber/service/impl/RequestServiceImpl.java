package com.example.uber.service.impl;

import com.example.uber.exceptions.custom.ResourceNotFoundException;
import com.example.uber.model.Driver;
import com.example.uber.model.Passenger;
import com.example.uber.model.Request;
import com.example.uber.model.enums.DriverStatus;
import com.example.uber.model.enums.RequestStatus;
import com.example.uber.model.request.RequestDriveRequest;
import com.example.uber.model.response.RequestDriveResponse;
import com.example.uber.repository.RequestRepository;
import com.example.uber.service.DriverService;
import com.example.uber.service.PassengerService;
import com.example.uber.service.RequestService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final DriverService driverService;
    private final PassengerService passengerService;
    private final ModelMapper modelMapper;

    @Override
    public RequestDriveResponse makeRequestForAllDrivers(UUID passengerId, RequestDriveRequest request) {
        Passenger passenger = passengerService.getById(passengerId);
        Request requestDrive = modelMapper.map(request, Request.class)
                .withStatus(RequestStatus.CREATED)
                .withPassenger(passenger);
        Request savedRequest = requestRepository.save(requestDrive);
        return modelMapper.map(savedRequest, RequestDriveResponse.class);
    }

    @Override
    public RequestDriveResponse makeRequestForSpecificDriver(UUID passengerId, UUID chosenDriverId, RequestDriveRequest request) {
        Passenger passenger = passengerService.getById(passengerId);
        Driver chosenDriver = driverService.getDriverById(chosenDriverId);
        Request requestDrive = modelMapper.map(request, Request.class)
                .withPassenger(passenger)
                .withChosenDriver(chosenDriver)
                .withStatus(RequestStatus.CREATED);
        Request savedRequest = requestRepository.save(requestDrive);
        return modelMapper.map(savedRequest, RequestDriveResponse.class);
    }

    @Override
    public List<RequestDriveResponse> getAllCreatedRequests(UUID driverId) {
        List<Request> requests = requestRepository.findAll();
        return requests.stream()
                .filter(request -> (request.getChosenDriver() == null || request.getChosenDriver().getId().equals(driverId))
                        && request.getStatus().equals(RequestStatus.CREATED))
                .map(request -> modelMapper.map(request, RequestDriveResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public RequestDriveResponse confirmRequest(UUID driverId, UUID requestToConfirmId) {
        Request request = this.getById(requestToConfirmId);
        Driver driver = driverService.getDriverById(driverId);
        Request savedRequest = requestRepository.save(request.withStatus(RequestStatus.CONFIRMED).withConfirmedByDriver(driver));
        driverService.changeStatusForDriver(driverId, DriverStatus.BUSY);
        return modelMapper.map(savedRequest, RequestDriveResponse.class);
    }

    @Override
    public Request getById(UUID requestId) {
        return requestRepository.findById(requestId).orElseThrow(() ->
                new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Request not found."));
    }

    @Override
    public RequestDriveResponse getRequestDriveResponse(UUID requestId) {
        Request request = getById(requestId);
        return modelMapper.map(request, RequestDriveResponse.class);
    }

    @Override
    public void updateStatus(UUID requestId, RequestStatus status) {
        Request request = getById(requestId);
        requestRepository.save(request.withStatus(status));
    }

}
