package com.example.booking_service.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long flightId;
    private Long hotelId;
    private LocalDate travelDate;
    private BigDecimal totalCost;
    private String status;


    public Booking() {
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setId(Long id) { this.id = id; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public void setHotelId(Long hotelId) { this.hotelId = hotelId; }
    public void setTravelDate(LocalDate travelDate) { this.travelDate = travelDate; }
    public void setTotalCost(BigDecimal totalCost) { this.totalCost = totalCost; }
    public void setStatus(String status) { this.status = status; }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
}