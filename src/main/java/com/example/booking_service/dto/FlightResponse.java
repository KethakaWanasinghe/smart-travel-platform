package com.example.booking_service.dto;
import java.math.BigDecimal;
import java.time.LocalDate;

public class FlightResponse {
    private Long flightId;
    private String departureCity;
    private String arrivalCity;
    private LocalDate travelDate;
    private BigDecimal price;
    private boolean isAvailable;

    public FlightResponse() {}

    public FlightResponse(Long flightId, String departureCity, String arrivalCity, LocalDate travelDate, BigDecimal price, boolean isAvailable) {
        this.flightId = flightId;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.travelDate = travelDate;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public BigDecimal getPrice() { return price; }
    public boolean isAvailable() { return isAvailable; }

    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public String getDepartureCity() { return departureCity; }
    public void setDepartureCity(String departureCity) { this.departureCity = departureCity; }
    public String getArrivalCity() { return arrivalCity; }
    public void setArrivalCity(String arrivalCity) { this.arrivalCity = arrivalCity; }
    public LocalDate getTravelDate() { return travelDate; }
    public void setTravelDate(LocalDate travelDate) { this.travelDate = travelDate; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setAvailable(boolean available) { isAvailable = available; }
}