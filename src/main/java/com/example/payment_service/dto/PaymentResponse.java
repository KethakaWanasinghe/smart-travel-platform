package com.example.payment_service.dto;

public class PaymentResponse {
    private String paymentReferenceId;
    private String status;

    public PaymentResponse() {}

    public PaymentResponse(String paymentReferenceId, String status) {
        this.paymentReferenceId = paymentReferenceId;
        this.status = status;
    }

    public String getPaymentReferenceId() { return paymentReferenceId; }
    public void setPaymentReferenceId(String paymentReferenceId) { this.paymentReferenceId = paymentReferenceId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}