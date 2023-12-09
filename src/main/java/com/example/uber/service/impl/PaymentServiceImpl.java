package com.example.uber.service.impl;

import com.example.uber.model.Drive;
import com.example.uber.model.Passenger;
import com.example.uber.model.Payment;
import com.example.uber.model.response.PaymentResponse;
import com.example.uber.repository.PaymentRepository;
import com.example.uber.service.PaymentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;

    public Payment getById(UUID paymentId){
        return paymentRepository.findById(paymentId).orElseThrow();
    }

    @Override
    public PaymentResponse addPayment(Drive drive, Passenger passenger, float totalSumPayed) {
        Payment payment = new Payment(totalSumPayed, drive, passenger);
        Payment savedPayment = paymentRepository.save(payment);
        return modelMapper.map(savedPayment, PaymentResponse.class);
    }

    @Override
    public List<PaymentResponse> getAllPayment() {
        return paymentRepository.findAll()
                .stream()
                .map(payment -> modelMapper.map(payment, PaymentResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean addDriverTipForPayment(UUID paymentId, float driverTipSum) {
        Payment payment = getById(paymentId);
        Payment paymentWithDriverTip = payment.withDriverTip(driverTipSum);
        paymentRepository.save(paymentWithDriverTip);
        return true;
    }
}
