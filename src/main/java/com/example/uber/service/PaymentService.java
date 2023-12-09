package com.example.uber.service;

import com.example.uber.model.Drive;
import com.example.uber.model.Passenger;
import com.example.uber.model.response.PaymentResponse;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
    PaymentResponse addPayment(Drive drive, Passenger passenger, float totalSumPayed);

    List<PaymentResponse> getAllPayment();

    Boolean addDriverTipForPayment(UUID paymentId, float driverTipSum);
}
