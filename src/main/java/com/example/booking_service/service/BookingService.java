package com.example.booking_service.service;
import com.example.booking_service.client.FlightClient;
import com.example.booking_service.client.HotelClient;
import com.example.booking_service.dto.BookingRequest;
import com.example.booking_service.dto.FlightResponse;
import com.example.booking_service.dto.HotelResponse;
import com.example.booking_service.dto.PaymentRequest;
import com.example.booking_service.dto.PaymentResponse;
import com.example.booking_service.dto.NotificationRequest;
import com.example.booking_service.model.Booking;
import com.example.booking_service.repository.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final FlightClient flightClient;
    private final HotelClient hotelClient;
    private final WebClient webClient;

    // Constants for WebClient URLs
    private static final String USER_SERVICE_URL = "http://localhost:8081/api/v1/users/{userId}/validate";
    private static final String PAYMENT_SERVICE_URL = "http://localhost:8084/api/v1/payments/process";
    private static final String NOTIFICATION_SERVICE_URL = "http://localhost:8085/api/v1/notifications/send";


    @Autowired
    public BookingService(BookingRepository bookingRepository, FlightClient flightClient, HotelClient hotelClient, WebClient.Builder webClientBuilder) {
        this.bookingRepository = bookingRepository;
        this.flightClient = flightClient;
        this.hotelClient = hotelClient;
        // Build WebClient instance
        this.webClient = webClientBuilder.build();
    }

    @Transactional
    public Booking createBooking(BookingRequest request) {

        BigDecimal totalCost = BigDecimal.ZERO;

        if (!validateUser(request.getUserId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User ID " + request.getUserId() + " is not valid.");
        }

        FlightResponse flight = flightClient.checkAvailability(request.getFlightId()).getBody();
        if (flight == null || !flight.isAvailable()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Flight ID " + request.getFlightId() + " is unavailable.");
        }
        totalCost = totalCost.add(flight.getPrice());


        HotelResponse hotel = hotelClient.checkAvailability(request.getHotelId()).getBody();
        if (hotel == null || !hotel.isAvailable()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hotel ID " + request.getHotelId() + " is unavailable.");
        }
        totalCost = totalCost.add(hotel.getNightlyRate());


        Booking booking = new Booking();
        booking.setUserId(request.getUserId());
        booking.setFlightId(request.getFlightId());
        booking.setHotelId(request.getHotelId());
        booking.setTravelDate(request.getTravelDate());
        booking.setTotalCost(totalCost);
        booking.setStatus("PENDING");
        booking = bookingRepository.save(booking);


        PaymentRequest paymentRequest = new PaymentRequest(booking.getId(), booking.getUserId(), totalCost);
        PaymentResponse paymentResponse = callPaymentService(paymentRequest);

        if (paymentResponse == null || paymentResponse.getStatus().equals("FAILED")) {

            booking.setStatus("FAILED");
            bookingRepository.save(booking);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment failed to initiate or responded FAILED.");
        }


        return booking;
    }


    @Transactional
    public void updateBookingStatus(Long bookingId, String status) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found with ID: " + bookingId));

        booking.setStatus(status);
        bookingRepository.save(booking);

        if ("CONFIRMED".equals(status)) {
            // 7. Call Notification Service (WebClient)
            sendNotification(booking);
        }
    }


    private boolean validateUser(Long userId) {

        return webClient.get()
                .uri(USER_SERVICE_URL, userId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .onErrorResume(e -> Mono.just(false)) // Treat service failure as validation failure
                .block();
    }

    private PaymentResponse callPaymentService(PaymentRequest request) {
        // WebClient call to Payment Service (8084)
        return webClient.post()
                .uri(PAYMENT_SERVICE_URL)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(PaymentResponse.class)
                .block();
    }

    private void sendNotification(Booking booking) {

        NotificationRequest notificationRequest = new NotificationRequest(
                booking.getId(),
                booking.getUserId(),
                "Your Smart Travel booking #" + booking.getId() + " is CONFIRMED!"
        );

        webClient.post()
                .uri(NOTIFICATION_SERVICE_URL)
                .bodyValue(notificationRequest)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}