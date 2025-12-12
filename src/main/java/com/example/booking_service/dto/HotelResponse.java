package com.example.booking_service.dto;
import java.math.BigDecimal;
import java.time.LocalDate;

public class HotelResponse {
    private Long hotelId;
    private String hotelName;
    private String roomType;
    private LocalDate checkInDate;
    private BigDecimal nightlyRate;
    private boolean isAvailable;

    public HotelResponse() {}

    public HotelResponse(Long hotelId, String hotelName, String roomType, LocalDate checkInDate, BigDecimal nightlyRate, boolean isAvailable) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.roomType = roomType;
        this.checkInDate = checkInDate;
        this.nightlyRate = nightlyRate;
        this.isAvailable = isAvailable;
    }

    public BigDecimal getNightlyRate() { return nightlyRate; }
    public boolean isAvailable() { return isAvailable; }


    public Long getHotelId() { return hotelId; }
    public void setHotelId(Long hotelId) { this.hotelId = hotelId; }
    public String getHotelName() { return hotelName; }
}