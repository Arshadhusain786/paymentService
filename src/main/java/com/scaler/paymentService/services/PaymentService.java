package com.scaler.paymentService.services;

import com.scaler.paymentService.dtos.CreateOrderRequest;
import com.scaler.paymentService.dtos.CreateOrderResponse;
import com.scaler.paymentService.dtos.PaymentVerificationRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class PaymentService {

    @Value("${razorpay.key.id}")
    private String keyId;

    @Value("${razorpay.key.secret}")
    private String keySecret;

    private final String PAYMENT_LINK_URL = "https://api.razorpay.com/v1/payment_links";

    // Create payment link using Razorpay REST API
    public CreateOrderResponse createOrder(CreateOrderRequest request) {

        try {
            RestTemplate restTemplate = new RestTemplate();

            // Prepare payload
            JSONObject payload = new JSONObject();
            payload.put("amount", (int) (request.getAmount() * 100)); // in paise
            payload.put("currency", request.getCurrency());
            payload.put("description", request.getDescription() != null ? request.getDescription() : "Payment");
            payload.put("reference_id", request.getReceipt() != null ? request.getReceipt() : "txn_" + System.currentTimeMillis());
            payload.put("accept_partial", false);

            // Customer info (optional)
            if (request.getCustomerName() != null || request.getCustomerEmail() != null || request.getCustomerContact() != null) {
                JSONObject customer = new JSONObject();
                if (request.getCustomerName() != null) customer.put("name", request.getCustomerName());
                if (request.getCustomerEmail() != null) customer.put("email", request.getCustomerEmail());
                if (request.getCustomerContact() != null) customer.put("contact", request.getCustomerContact());
                payload.put("customer", customer);
            }

            // Prepare headers with Basic Auth
            HttpHeaders headers = new HttpHeaders();
            String auth = keyId + ":" + keySecret;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
            headers.set("Authorization", "Basic " + encodedAuth);
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(payload.toString(), headers);

            // Call Razorpay API
            ResponseEntity<String> response = restTemplate.exchange(PAYMENT_LINK_URL, HttpMethod.POST, entity, String.class);

            JSONObject jsonResponse = new JSONObject(response.getBody());

            // Build and return response DTO
            return new CreateOrderResponse(
                    jsonResponse.getString("id"),
                    jsonResponse.getString("currency"),
                    jsonResponse.getInt("amount"),
                    jsonResponse.getString("status"),
                    jsonResponse.getString("short_url") // hosted payment page link
            );

        } catch (Exception e) {
            e.printStackTrace();
            return null; // or handle exception properly
        }
    }

    // Verify payment signature (same as before)
    public boolean verifySignature(PaymentVerificationRequest request) {
        try {
            JSONObject attributes = new JSONObject();
            attributes.put("razorpay_order_id", request.getRazorpayOrderId());
            attributes.put("razorpay_payment_id", request.getRazorpayPaymentId());
            attributes.put("razorpay_signature", request.getRazorpaySignature());

            com.razorpay.Utils.verifyPaymentSignature(attributes, keySecret);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
