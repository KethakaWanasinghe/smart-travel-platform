package com.example.booking_service.controller;
import com.example.booking_service.dto.BookingRequest;
import com.example.booking_service.model.Booking;
import com.example.booking_service.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest request) {
        Booking newBooking = bookingService.createBooking(request);

        return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
    }

    @PutMapping("/{bookingId}/status")
    public ResponseEntity<Void> updateBookingStatus(
            @PathVariable Long bookingId,
            @RequestBody String status) {

        bookingService.updateBookingStatus(bookingId, status);

        return ResponseEntity.noContent().build();
    }
}