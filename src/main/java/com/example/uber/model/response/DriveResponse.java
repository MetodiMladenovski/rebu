package com.example.uber.model.response;

import com.example.uber.model.Car;
import com.example.uber.model.Driver;
import com.example.uber.model.Request;
import com.example.uber.model.enums.DriveStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DriveResponse {
    private UUID id;
    private float grade;
    private float kmTravelled;
    private Timestamp startTime;
    private float destinationLatitude;
    private float destinationLongitude;
    private DriveStatus status;
    private Car car;
    private Driver driver;
    private Request request;
}
