package com.example.hotel_service.dto;
import java.math.BigDecimal;
import java.time.LocalDate;

public class HotelResponse {
    private Long hotelId;
    private String hotelName;
    private String roomType;
    private LocalDate checkInDate;
    private BigDecimal nightlyRate;
    private boolean isAvailable;

    public HotelResponse() {
    }

    public HotelResponse(Long hotelId, String hotelName, String roomType, LocalDate checkInDate, BigDecimal nightlyRate, boolean isAvailable) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.roomType = roomType;
        this.checkInDate = checkInDate;
        this.nightlyRate = nightlyRate;
        this.isAvailable = isAvailable;
    }


    public Long getHotelId() { return hotelId; }
    public String getHotelName() { return hotelName; }
    public String getRoomType() { return roomType; }
    public LocalDate getCheckInDate() { return checkInDate; }
    public BigDecimal getNightlyRate() { return nightlyRate; }
    public boolean isAvailable() { return isAvailable; }


    public void setHotelId(Long hotelId) { this.hotelId = hotelId; }
    public void setHotelName(String hotelName) { this.hotelName = hotelName; }
    public void setRoomType(String roomType) { this.roomType = roomType; }
    public void setCheckInDate(LocalDate checkInDate) { this.checkInDate = checkInDate; }
    public void setNightlyRate(BigDecimal nightlyRate) { this.nightlyRate = nightlyRate; }
    public void setAvailable(boolean available) { isAvailable = available; }
}