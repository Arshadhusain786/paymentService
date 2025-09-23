package com.scaler.paymentService.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitiatePaymentRequestDto {
   private String orderId;
   private String email;
   private String phoneNumber;
   private Long amount;
}
