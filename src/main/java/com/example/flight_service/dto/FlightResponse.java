package com.example.flight_service.dto;
import java.math.BigDecimal;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;

public class FlightResponse {

    @JsonProperty("flightId")
    private Long flightId;
    @JsonProperty("departureCity")
    private String departureCity;
    @JsonProperty("arrivalCity")
    private String arrivalCity;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("travelDate")
    private LocalDate travelDate;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("isAvailable")
    private boolean isAvailable;

    public FlightResponse() {
    }

    public FlightResponse(Long flightId, String departureCity, String arrivalCity, LocalDate travelDate, BigDecimal price, boolean isAvailable) {
        this.flightId = flightId;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.travelDate = travelDate;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public Long getFlightId() {
        return flightId;
    }
    public String getDepartureCity() {
        return departureCity;
    }
    public String getArrivalCity() {
        return arrivalCity;
    }
    public LocalDate getTravelDate() {
        return travelDate;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public boolean isAvailable() {
        return isAvailable;
    }


    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }
    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }
    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }
    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}