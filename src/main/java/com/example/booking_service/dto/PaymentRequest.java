package com.example.booking_service.dto;

import java.math.BigDecimal;

public class PaymentRequest {
    private Long bookingId;
    private Long userId;
    private BigDecimal amount;

    public PaymentRequest() {}

    public PaymentRequest(Long bookingId, Long userId, BigDecimal amount) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.amount = amount;
    }

    public Long getBookingId() { return bookingId; }
    public Long getUserId() { return userId; }
    public BigDecimal getAmount() { return amount; }


        public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
        public void setUserId(Long userId) { this.userId = userId; }
        public void setAmount(BigDecimal amount) { this.amount = amount; }

}