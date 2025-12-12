package com.example.notification_service.service;
import com.example.notification_service.dto.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendNotification(NotificationRequest request) {

        System.out.println("------------------------------------------");
        System.out.println("Notification Service (8085) received request:");
        System.out.println("  Booking ID: " + request.getBookingId());
        System.out.println("  Message: " + request.getMessage());
        System.out.println("Notification successfully processed (Simulated).");
        System.out.println("------------------------------------------");
    }
}