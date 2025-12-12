package com.example.notification_service.dto;

public class NotificationRequest {
    private Long bookingId;
    private Long userId;
    private String message;

    public NotificationRequest() {}
    public NotificationRequest(Long bookingId, Long userId, String message) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.message = message;
    }

    public Long getBookingId() { return bookingId; }
    public Long getUserId() { return userId; }
    public String getMessage() { return message; }

    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setMessage(String message) { this.message = message; }
}