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
@Table(name = "driver_report")
@Getter
@NoArgsConstructor
public class DriverReport {
    @Id
    private UUID id;
    private UUID driverId;
    private UUID passengerId;
    private String passengerEmail;
    private String passengerName;
    private String passengerSurname;
    private float kmTravelledWithPassenger;
    private float totalEarnings;
    private float earningsPerKm;
    private float averageGradeReceivedPerDrive;
    private long numberOfDrives;

}
