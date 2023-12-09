package com.example.uber.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DriveRequest {
    private float destinationLatitude;
    private float destinationLongitude;
}
