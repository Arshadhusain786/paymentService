package com.scaler.paymentService.adapters.paymentgatewaysadapters.razorpay;


//package com.scaler.paymentService.config;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayConfiguration{

    @Bean
    public RazorpayClient razorpayClient() throws RazorpayException {
        String keyId = System.getenv("RAZORPAY_KEY_ID");
        String keySecret = System.getenv("RAZORPAY_KEY_SECRET");

        if (keyId == null || keySecret == null) {
            throw new IllegalStateException("Razorpay keys not set in environment variables");
        }

        System.out.println("🔑 Using Razorpay Key ID: " + keyId); // for debug only
        return new RazorpayClient(keyId, keySecret);
    }
}
