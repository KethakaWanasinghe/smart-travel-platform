package com.example.booking_service.dto;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;

public class BookingRequest {
    @JsonProperty("userId")
    private Long userId;
    @JsonProperty("flightId")
    private Long flightId;
    @JsonProperty("hotelId")
    private Long hotelId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("travelDate")
    private LocalDate travelDate;

    public BookingRequest() { }

    public BookingRequest(Long userId, Long flightId, Long hotelId, LocalDate travelDate) {
        this.userId = userId;
        this.flightId = flightId;
        this.hotelId = hotelId;
        this.travelDate = travelDate;
    }

    public Long getUserId() { return userId; }
    public Long getFlightId() { return flightId; }
    public Long getHotelId() { return hotelId; }
    public LocalDate getTravelDate() { return travelDate; }

    public void setUserId(Long userId) { this.userId = userId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public void setHotelId(Long hotelId) { this.hotelId = hotelId; }
    public void setTravelDate(LocalDate travelDate) { this.travelDate = travelDate; }
}