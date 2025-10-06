package com.scaler.paymentService.controllers;

import com.scaler.paymentService.dtos.CreateOrderRequest;
import com.scaler.paymentService.dtos.CreateOrderResponse;
import com.scaler.paymentService.dtos.PaymentVerificationRequest;
import com.scaler.paymentService.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Endpoint to create order and return hosted payment page link
    @PostMapping("/create-order")
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        try {
            CreateOrderResponse response = paymentService.createOrder(request);
            if (response != null) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(500)
                        .body(new CreateOrderResponse(null, null, 0, "failed", null));
            }
        } catch (Exception e) { // Catch all exceptions from REST API call
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body(new CreateOrderResponse(null, null, 0, "failed", null));
        }
    }

    // Endpoint to verify payment signature
    @PostMapping("/verify")
    public ResponseEntity<String> verifyPayment(@RequestBody PaymentVerificationRequest request) {
        boolean isValid = paymentService.verifySignature(request);
        if (isValid) {
            return ResponseEntity.ok("Payment Signature Verified");
        } else {
            return ResponseEntity.status(400).body("Payment Verification Failed");
        }
    }
}
