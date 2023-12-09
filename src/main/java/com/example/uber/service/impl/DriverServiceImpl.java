package com.example.uber.service.impl;

import com.example.uber.exceptions.custom.FileToBytesFailedException;
import com.example.uber.exceptions.custom.ProfilePictureMissingException;
import com.example.uber.exceptions.custom.ResourceNotFoundException;
import com.example.uber.exceptions.custom.WrongImageFormatException;
import com.example.uber.model.Drive;
import com.example.uber.model.Driver;
import com.example.uber.model.enums.DriverStatus;
import com.example.uber.model.response.DriverResponse;
import com.example.uber.repository.DriverRepository;
import com.example.uber.service.DriverService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final ModelMapper modelMapper;

    @Override
    public Driver getDriverById(UUID driverId) {
        return driverRepository.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Driver not found"));
    }

    @Override
    public List<Driver> findAllApprovedAndAvailableDrivers() {
        return driverRepository.findAll().stream()
                .filter(driver -> driver.getStatus().equals(DriverStatus.AVAILABLE) && driver.isApproved())
                .collect(Collectors.toList());
    }

    @Override
    public Boolean approveAccount(UUID driverId) {
        Driver driver = getDriverById(driverId).withApproved(true);
        driverRepository.save(driver);
        return true;
    }

    @Override
    public List<Driver> findAllUnapprovedDrivers() {
        return driverRepository.findAllByIsApprovedAndIsDenied(false, false);
    }

    @Override
    public void updateGradeForDriver(UUID driverId, float grade, List<Drive> drives) {
        Driver driver = getDriverById(driverId);
        List<Float> gradesForDriver = drives.stream().map(Drive::getGrade).collect(Collectors.toList());
        driver.updateGrade(gradesForDriver, grade);
        driverRepository.save(driver);
    }

    @Override
    public DriverResponse changeProfilePicture(UUID driverId, MultipartFile file) {
        Driver driver = getDriverById(driverId);
        checkIfFileIsImage(file);
        Driver driverWithPicture = driver.withProfilePicture(this.getBytesFromFile(file));
        driverRepository.save(driverWithPicture);
        return modelMapper.map(driverWithPicture, DriverResponse.class);
    }

    private void checkIfFileIsImage(MultipartFile file) {
        String fileType = file.getContentType();
        if (!Objects.equals(fileType, "image/jpeg") && !Objects.equals(fileType, "image/png")) {
            throw new WrongImageFormatException(HttpStatus.CONFLICT, "The image was provided in a wrong format");
        }
    }

    private byte[] getBytesFromFile(MultipartFile file) {
        byte[] bytes;
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            throw new FileToBytesFailedException(HttpStatus.NOT_ACCEPTABLE, "The provided file was bad. getBytes() failed.");
        }
        return bytes;
    }

    @Override
    public Resource getProfilePicture(UUID driverId) {
        Driver driver = getDriverById(driverId);
        if (driver.getProfilePicture() == null) {
            throw new ProfilePictureMissingException(HttpStatus.NOT_FOUND, "Driver doesn't have a picture.");
        }
        byte[] profilePictureBytes = driver.getProfilePicture();
        return new ByteArrayResource(profilePictureBytes);
    }

    @Override
    public Boolean denyAccount(UUID driverId) {
        Driver driver = getDriverById(driverId).withDenied(true);
        driverRepository.save(driver);
        return true;
    }

    public void changeStatusForDriver(UUID driverId, DriverStatus status) {
        Driver driver = driverRepository.findById(driverId).orElseThrow(IllegalAccessError::new);
        Driver driverWithChangedStatus = driver.withStatus(status);
        driverRepository.save(driverWithChangedStatus);
    }
}
