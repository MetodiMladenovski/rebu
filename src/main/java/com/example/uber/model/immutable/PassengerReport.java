package com.example.uber.model.immutable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Immutable
@Table(name = "passenger_report")
@Getter
@NoArgsConstructor
public class PassengerReport implements Serializable {
    @Id
    private UUID id;
    private UUID passengerId;
    private UUID driverId;
    private String driverName;
    private String driverSurname;
    private float kmTravelledWithDriver;
    private float totalPricePaid;
    private float pricePerKm;
}
