package com.upgrad.hirewheels.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Vehicle {

    @Id
    @Column(length = 10)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int vehicleId;

    @Column(length = 50, nullable = false)
    @NonNull
    private String vehicleModel;

    @Column(length = 10, nullable = false, columnDefinition = "CHAR")
    @NonNull
    private String vehicleNumber;

    @Column(length = 50, nullable = false)
    @NonNull
    private String color;

    @Column(length = 1, nullable = false)
    @NonNull
    private int availabilityStatus;

    @Column(length = 500, nullable = false)
    @NonNull
    private String vehicleImageUrl;

    @ManyToOne
    @JoinColumn(name = "vehicle_subcategory_id", nullable = false)
    @NonNull
    private VehicleSubCategory vehicleSubCategory;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    @NonNull
    private Location location;

    @ManyToOne
    @JoinColumn(name = "fuel_type_id", nullable = false)
    @NonNull
    private FuelType fuelType;

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleSubCategory=" + vehicleSubCategory +
                ", color='" + color + '\'' +
                ", location=" + location +
                ", fuelType=" + fuelType +
                ", availabilityStatus=" + availabilityStatus +
                ", vehicleImageUrl='" + vehicleImageUrl + '\'' +
                '}';
    }
}
