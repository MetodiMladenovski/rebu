package com.example.uber.model;

import com.example.uber.model.enums.DriveStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.With;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "drive")
@Getter
@Setter
@AllArgsConstructor
public class Drive {

    @Id
    @GeneratedValue
    @Column(name = "drive_id")
    private UUID id;

    @Column(name = "grade")
    @With
    private float grade;

    @Column(name = "km_travelled")
    @With
    private float kmTravelled;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @Column(name = "destination_latitude")
    private float destinationLatitude;

    @Column(name = "destination_longitude")
    private float destinationLongitude;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @With
    private DriveStatus status;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @OneToOne
    @JoinColumn(name = "request_id", nullable = false)
    private Request request;

    public Drive() {
    }

    public Drive(Car car, Driver driver, Request request, float destinationLatitude, float destinationLongitude) {
        this.kmTravelled = (float) 0.0;
        this.startTime = Timestamp.valueOf(LocalDateTime.now());
        this.status = DriveStatus.STARTED;
        this.car = car;
        this.driver = driver;
        this.request = request;
        this.destinationLatitude = destinationLatitude;
        this.destinationLongitude = destinationLongitude;
    }
}
