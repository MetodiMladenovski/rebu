package com.example.uber.model.immutable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Immutable
@Table(name = "admin_report")
@Getter
@NoArgsConstructor
public class AdminReport {
    @Id
    private UUID id;
    private UUID driverId;
    private String driverEmail;
    private String driverName;
    private String driverSurname;
    private String make;
    private String model;
    private float driverGrade;
    private long numberOfDrives;
    private float totalMoneyMade;
    private long numberOfDifferentRequests;
    private long numberOfDifferentPassengers;
    private float averageMoneyPerRequest;
    private float totalKmTravelled;
}
