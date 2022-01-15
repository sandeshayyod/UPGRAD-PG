package com.upgrad.hirewheels.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Location {

    @Id
    @Column(length = 10)
    @NonNull
    private int locationId;

    @NonNull
    @Column(length = 50, nullable = false)
    private String locationName;

    @NonNull
    @Column(length = 100, nullable = false)
    private String address;

    @NonNull
    @Column(length = 6, nullable = false, columnDefinition = "CHAR")
    private int pincode;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    @NonNull
    private City city;

    @OneToMany(mappedBy = "location", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Booking> bookings;

    @OneToMany(mappedBy = "location", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Vehicle> vehicles;

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", locationName='" + locationName + '\'' +
                ", address='" + address + '\'' +
                ", pincode=" + pincode +
                ", city=" + city +
                ", bookings=" + bookings.stream().map(Booking::getBookingId).collect(Collectors.toSet()) +
                ", vehicles=" + vehicles.stream().map(Vehicle::getVehicleId).collect(Collectors.toSet()) +
                '}';
    }
}
