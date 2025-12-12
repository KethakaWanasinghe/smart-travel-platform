package com.example.flight_service.service;
import com.example.flight_service.dto.FlightResponse;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class FlightService {

    public FlightResponse checkFlightAvailability(Long flightId) {

        // ID 200 - available.
        // ID 100 - 'unavailable'.
        if (flightId != null && flightId.equals(200L)) {
            return new FlightResponse(
                    flightId,
                    "New York",
                    "London",
                    LocalDate.of(2025, 1, 10),
                    new BigDecimal("550.00"),
                    true
            );
        } else if (flightId != null && flightId.equals(100L)) {
            return new FlightResponse(
                    flightId,
                    "Paris",
                    "Rome",
                    LocalDate.of(2025, 1, 10),
                    new BigDecimal("0.00"),
                    false
            );
        } else {
            // Default
            return new FlightResponse(
                    flightId, null, null, null, null, false
            );
        }
    }
}