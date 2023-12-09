package com.example.uber.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.With;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "car")
@Getter
@Setter
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue
    @Column(name = "car_id")
    private UUID id;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "make")
    private String make;

    @Column(name = "car_year")
    private int year;

    @Column(name = "model")
    private String model;

    @OneToOne
    @JoinColumn(name = "driver_id", nullable = false)
    @With
    private Driver driver;

    public Car() {
    }
}
