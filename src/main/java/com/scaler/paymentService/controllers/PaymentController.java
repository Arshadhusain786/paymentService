package com.scaler.paymentService.controllers;

import com.scaler.paymentService.dtos.CreatePaymentLinkRequestDto;
import com.scaler.paymentService.dtos.InitiatePaymentRequestDto;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

//    @GetMapping("/")
//    public String sayHello(){
//        return "hello";
//    }

    @PostMapping("/")
    public String initiatePayment(@RequestBody InitiatePaymentRequestDto request){
        return paymentService.initiatePayment(request.getOrderId(), request.getEmail(),
                request.getPhoneNumber(), request.getAmount());
    }
}