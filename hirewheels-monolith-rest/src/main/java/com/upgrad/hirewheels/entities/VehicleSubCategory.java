package com.upgrad.hirewheels.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class VehicleSubCategory {

    @Id
    @Column(length = 10)
    @NonNull
    private int vehicleSubcategoryId;

    @Column(length = 50, nullable = false, unique = true)
    @NonNull
    private String vehicleSubcategoryName;

    @Column(length = 10, precision = 2, nullable = false)
    @NonNull
    private double pricePerDay;

    @ManyToOne
    @JoinColumn(name = "vehicle_category_id", nullable = false)
    @NonNull
    private VehicleCategory vehicleCategory;

    @OneToMany(mappedBy = "vehicleSubCategory", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Vehicle> vehicles;

    @Override
    public String toString() {
        return "VehicleSubCategory{" +
                "vehicleSubcategoryId=" + vehicleSubcategoryId +
                ", vehicleSubcategoryName='" + vehicleSubcategoryName + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", vehicleCategory=" + vehicleCategory +
                ", vehicles=" + vehicles.stream().map(Vehicle::getVehicleId).collect(Collectors.toSet()) +
                '}';
    }
}
