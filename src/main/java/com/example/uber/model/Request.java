package com.example.uber.model;

import com.example.uber.model.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.With;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "request")
@Getter
@Setter
@AllArgsConstructor
public class Request {

    @Id
    @GeneratedValue
    @Column(name = "request_id")
    private UUID id;

    @Column(name = "status")
    @With
    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @Column(name = "city_address")
    private String cityAddress;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "number_address")
    private int numberAddress;

    @Column(name = "latitude")
    private float latitude;

    @Column(name = "longitude")
    private float longitude;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    @With
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "chosen_driver_id")
    @With
    private Driver chosenDriver;

    @ManyToOne
    @JoinColumn(name = "confirmed_by_driver_id")
    @With
    private Driver confirmedByDriver;

    public Request() {
    }

}
