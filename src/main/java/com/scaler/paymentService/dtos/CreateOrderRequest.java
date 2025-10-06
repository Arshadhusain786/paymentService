package com.scaler.paymentService.dtos;

public class CreateOrderRequest {

    private double amount;          // Amount in INR
    private String currency;        // Currency, e.g., "INR"
    private String receipt;         // Optional: custom receipt/reference id
    private String description;     // Optional: description of payment
    private String customerName;    // Optional: customer name
    private String customerEmail;   // Optional: customer email
    private String customerContact; // Optional: customer phone number

    public CreateOrderRequest() {}

    public CreateOrderRequest(double amount, String currency, String receipt, String description,
                              String customerName, String customerEmail, String customerContact) {
        this.amount = amount;
        this.currency = currency;
        this.receipt = receipt;
        this.description = description;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerContact = customerContact;
    }

    // Getters and Setters
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getReceipt() { return receipt; }
    public void setReceipt(String receipt) { this.receipt = receipt; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public String getCustomerContact() { return customerContact; }
    public void setCustomerContact(String customerContact) { this.customerContact = customerContact; }
}
