package com.example.hotel_service.controller;
import com.example.hotel_service.dto.HotelResponse;
import com.example.hotel_service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/{hotelId}/checkAvailability")
    public ResponseEntity<HotelResponse> checkAvailability(@PathVariable Long hotelId) {

        HotelResponse response = hotelService.checkHotelAvailability(hotelId);

        return ResponseEntity.ok(response);
    }
}