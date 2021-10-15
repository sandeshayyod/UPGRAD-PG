package com.upgrad.hirewheels.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @Column(length = 10)
    private int bookingId;

    @Column(nullable = false)
    private LocalDateTime pickupDate;

    @Column(nullable = false)
    private LocalDateTime dropoffDate;

    @Column(nullable = false)
    private LocalDateTime bookingDate;

    @Column(length = 10, precision = 2, nullable = false)
    private double amount;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", pickupDate=" + pickupDate +
                ", dropoffDate=" + dropoffDate +
                ", bookingDate=" + bookingDate +
                ", amount=" + amount +
                ", location=" + location +
                ", vehicle=" + vehicle +
                ", user=" + user +
                '}';
    }
}
