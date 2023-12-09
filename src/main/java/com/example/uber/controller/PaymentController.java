package com.example.uber.controller;

import com.example.uber.model.response.PaymentResponse;
import com.example.uber.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAllPayments(){
        return ResponseEntity.ok(paymentService.getAllPayment());
    }

    @PostMapping("/{paymentId}/tip")
    public ResponseEntity<Boolean> tipDriver(@PathVariable UUID paymentId, @RequestParam float driverTipSum){
        return ResponseEntity.ok(paymentService.addDriverTipForPayment(paymentId, driverTipSum));
    }
}
