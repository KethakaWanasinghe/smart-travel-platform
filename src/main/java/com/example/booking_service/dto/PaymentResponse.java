package com.example.booking_service.dto;

public class PaymentResponse {
    private String paymentReferenceId;
    private String status; // CONFIRMED or FAILED

    public PaymentResponse() {}

    public PaymentResponse(String paymentReferenceId, String status) {
        this.paymentReferenceId = paymentReferenceId;
        this.status = status;
    }

    public String getPaymentReferenceId() { return paymentReferenceId; }
    public String getStatus() { return status; }

    public void setPaymentReferenceId(String paymentReferenceId) { this.paymentReferenceId = paymentReferenceId; }
    public void setStatus(String status) { this.status = status; }

}