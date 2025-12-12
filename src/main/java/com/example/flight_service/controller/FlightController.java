package com.example.flight_service.controller;
import com.example.flight_service.dto.FlightResponse;
import com.example.flight_service.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/{flightId}/checkAvailability")
    public ResponseEntity<FlightResponse> checkAvailability(@PathVariable Long flightId) {

        FlightResponse response = flightService.checkFlightAvailability(flightId);

        return ResponseEntity.ok(response);
    }
}