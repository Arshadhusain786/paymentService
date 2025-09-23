package com.scaler.paymentService.services.paymentgateway;

import com.razorpay.Order;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class RazorpayPaymentGateway implements PaymentGateway {

    private RazorpayClient razorpayClient;

    public RazorpayPaymentGateway(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String generatePaymentLink(String orderId, String email, String phoneNumber, Long amount) {
        try {
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", amount);
            paymentLinkRequest.put("currency", "INR");
            paymentLinkRequest.put("accept_partial", false);
//        paymentLinkRequest.put("first_min_partial_amount",100);
            paymentLinkRequest.put("expire_by", 1707156500 + 3000);
            paymentLinkRequest.put("reference_id", orderId);
//            paymentLinkRequest.put("order_id", orderId);
            paymentLinkRequest.put("description", "Payment for order #" + orderId);
            JSONObject customer = new JSONObject();
            customer.put("name", phoneNumber);
            customer.put("contact", "Arshad Husain");
            customer.put("email", email);
            paymentLinkRequest.put("customer", customer);
            JSONObject notify = new JSONObject();
            notify.put("sms", true);
            notify.put("email", true);
            paymentLinkRequest.put("notify", notify);
            paymentLinkRequest.put("reminder_enable", true);
            JSONObject notes = new JSONObject();
//            notes.put("policy_name", "Jeevan Bima");
//            paymentLinkRequest.put("notes", notes);
            paymentLinkRequest.put("callback_url", "https://example-callback-url.com/");
            paymentLinkRequest.put("callback_method", "get");

            PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);

            return payment.get("short_url").toString();

        } catch (Exception e) {
            System.out.println(e.toString());
            return "something is wrong";
        }

    }

    public Order generateOrder() throws RazorpayException {
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", 50000);
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "receipt#1");
        JSONObject notes = new JSONObject();
        notes.put("notes_key_1", "Tea, Earl Grey, Hot");
        notes.put("notes_key_1", "Tea, Earl Grey, Hot");
        orderRequest.put("notes", notes);

        Order order = razorpayClient.orders.create(orderRequest);

        return order;
    }
}