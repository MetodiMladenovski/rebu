package com.example.uber.model.response;

import com.example.uber.model.Drive;
import com.example.uber.model.Passenger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PaymentResponse {
    private UUID id;
    private float totalSumPayed;
    private Drive drive;
    private Passenger passenger;
    private float driverTip;
}
