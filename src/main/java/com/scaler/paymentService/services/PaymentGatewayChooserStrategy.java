package com.scaler.paymentService.services;

import com.scaler.paymentService.services.paymentgateway.PaymentGateway;
import com.scaler.paymentService.services.paymentgateway.RazorpayPaymentGateway;
import com.scaler.paymentService.services.paymentgateway.StripePaymentGateway;
import org.springframework.stereotype.Service;

@Service
public class PaymentGatewayChooserStrategy
{
    private RazorpayPaymentGateway razorpayPaymentGateway;
    private StripePaymentGateway stripePaymentGateway;

    public PaymentGatewayChooserStrategy(RazorpayPaymentGateway razorpayPaymentGateway, StripePaymentGateway stripePaymentGateway) {
        this.razorpayPaymentGateway = razorpayPaymentGateway;
        this.stripePaymentGateway = stripePaymentGateway;
    }

    public PaymentGateway getBestPaymentGateway() {

//        int randomInt = new Random().nextInt();
//
//        if(randomInt%2 == 0){
//            return razorpayPaymentGateway;
//        }
//
//
//        return stripePaymentGateway;

        return stripePaymentGateway;

    }
}