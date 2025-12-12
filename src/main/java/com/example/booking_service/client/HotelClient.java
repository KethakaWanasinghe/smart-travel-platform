package com.example.booking_service.client;
import com.example.booking_service.dto.HotelResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hotel-service", url = "http://localhost:8083")
public interface HotelClient {
    @GetMapping("/api/v1/hotels/{hotelId}/checkAvailability")
    ResponseEntity<HotelResponse> checkAvailability(@PathVariable Long hotelId);
}