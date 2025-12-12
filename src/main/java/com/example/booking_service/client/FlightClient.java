package com.example.booking_service.client;
import com.example.booking_service.dto.FlightResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "flight-service", url = "http://localhost:8082")
public interface FlightClient {
    @GetMapping("/api/v1/flights/{flightId}/checkAvailability")
    ResponseEntity<FlightResponse> checkAvailability(@PathVariable Long flightId);
}