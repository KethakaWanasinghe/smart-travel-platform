package com.example.payment_service.service;
import com.example.payment_service.dto.PaymentRequest;
import com.example.payment_service.dto.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@Service
public class PaymentService {

    private final WebClient webClient;
    private static final String BOOKING_SERVICE_URL = "http://localhost:8080/api/v1/bookings";

    @Autowired
    public PaymentService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(BOOKING_SERVICE_URL).build();
    }

    public PaymentResponse processPayment(PaymentRequest request) {

        // Success for User ID 1
        boolean paymentSuccess = request.getUserId() != null && Objects.equals(request.getUserId(), 1L);
        String status = paymentSuccess ? "CONFIRMED" : "FAILED";
        String refId = UUID.randomUUID().toString();

        PaymentResponse response = new PaymentResponse(refId, status);

        try {
            updateBookingStatus(request.getBookingId(), status);
        } catch (Exception e) {
            System.err.println("Failed to notify Booking Service of status: " + e.getMessage());
        }

        return response;
    }

    private void updateBookingStatus(Long bookingId, String status) {

        this.webClient.put()
                .uri("/{bookingId}/status", bookingId) // Target: http://localhost:8080/api/v1/bookings/{id}/status
                .bodyValue(status)
                .retrieve()
                .onStatus(s -> s.isError(), clientResponse -> {
                    // If Booking Service fails to process the update (e.g., returns 404/500), throw error
                    return Mono.error(new RuntimeException("Booking status update failed"));
                })
                .toBodilessEntity()
                .block();
    }
}