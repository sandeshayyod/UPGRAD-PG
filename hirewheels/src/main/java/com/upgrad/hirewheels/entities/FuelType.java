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
public class FuelType {

    @Id
    @Column(length = 10)
    @NonNull
    private int fuelTypeId;

    @Column(length = 50, nullable = false, unique = true)
    @NonNull
    private String fuelType;

    @OneToMany(mappedBy = "fuelType", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Vehicle> vehicle;

    @Override
    public String toString() {
        return "FuelType{" +
                "fuelTypeId=" + fuelTypeId +
                ", fuelType='" + fuelType + '\'' +
                ", vehicle=" + vehicle.stream().map(Vehicle::getVehicleId).collect(Collectors.toSet()) +
                '}';
    }
}
