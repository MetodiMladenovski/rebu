package com.example.uber.model.response;

import com.example.uber.model.Driver;
import com.example.uber.model.Passenger;
import com.example.uber.model.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestDriveResponse {
    private UUID id;
    private RequestStatus status;
    private String cityAddress;
    private String streetAddress;
    private int numberAddress;
    private float latitude;
    private float longitude;
    private Passenger passenger;
    private Driver chosenDriver;
    private Driver confirmedByDriver;
}
