package com.example.uber.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RequestDriveRequest {
    private String cityAddress;
    private String streetAddress;
    private int numberAddress;
    private float latitude;
    private float longitude;
    private String chosenDriverId;
}
