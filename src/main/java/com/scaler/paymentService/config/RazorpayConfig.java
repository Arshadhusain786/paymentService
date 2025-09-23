package com.scaler.paymentService.config;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayConfig {

    @Bean
    public RazorpayClient createRazorpayClient() throws RazorpayException {
        // Read API keys from environment variables
        String razorpayKeyId = System.getenv("RAZORPAY_KEY_ID");
        String razorpayKeySecret = System.getenv("RAZORPAY_KEY_SECRET");

        if (razorpayKeyId == null || razorpayKeySecret == null) {
            throw new IllegalStateException("Razorpay API keys are not set in environment variables!");
        }

        return new RazorpayClient(razorpayKeyId, razorpayKeySecret);
    }
}
