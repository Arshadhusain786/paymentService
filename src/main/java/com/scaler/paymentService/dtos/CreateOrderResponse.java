package com.scaler.paymentService.dtos;

public class CreateOrderResponse {
    private String id;
    private String currency;
    private int amount;
    private String status;
    private String paymentPageUrl; // This is the hosted payment page link

    public CreateOrderResponse(String id, String currency, int amount, String status, String paymentPageUrl) {
        this.id = id;
        this.currency = currency;
        this.amount = amount;
        this.status = status;
        this.paymentPageUrl = paymentPageUrl;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPaymentPageUrl() { return paymentPageUrl; }
    public void setPaymentPageUrl(String paymentPageUrl) { this.paymentPageUrl = paymentPageUrl; }
}
