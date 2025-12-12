package com.example.payment_service.dto;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentRequest {
    @JsonProperty("bookingId")
    private Long bookingId;
    @JsonProperty("userId")
    private Long userId;
    @JsonProperty("amount")
    private BigDecimal amount;

    public PaymentRequest() {}

    public PaymentRequest(Long bookingId, Long userId, BigDecimal amount) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.amount = amount;
    }

    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}