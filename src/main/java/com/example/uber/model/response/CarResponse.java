package com.example.uber.model.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CarResponse {
    private UUID id;
    private String licensePlate;
    private String make;
    private int year;
    private String model;
}
