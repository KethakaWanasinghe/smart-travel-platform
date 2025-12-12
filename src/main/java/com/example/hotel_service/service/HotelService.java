package com.example.hotel_service.service;

import com.example.hotel_service.dto.HotelResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Service
public class HotelService {

    public HotelResponse checkHotelAvailability(Long hotelId) {

        // Hotel ID 55
        if (Objects.equals(hotelId, 55L)) {
            return new HotelResponse(
                    hotelId,
                    "The Grand Hotel",
                    "Deluxe Suite",
                    LocalDate.of(2025, 1, 10),
                    new BigDecimal("250.00"),
                    true
            );
        } else {
            // Default
            return new HotelResponse(
                    hotelId, null, null, null, null, false
            );
        }
    }
}