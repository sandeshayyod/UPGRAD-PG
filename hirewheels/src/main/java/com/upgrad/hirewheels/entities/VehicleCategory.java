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
public class VehicleCategory {

    @Id
    @Column(length = 10)
    @NonNull
    private int vehicleCategoryId;

    @Column(length = 50, nullable = false, unique = true)
    @NonNull
    private String vehicleCategoryName;

    @OneToMany(mappedBy = "vehicleCategory", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<VehicleSubCategory> vehicleSubcategories;

    @Override
    public String toString() {
        return "VehicleCategory{" +
                "vehicleCategoryId=" + vehicleCategoryId +
                ", vehicleCategoryName='" + vehicleCategoryName + '\'' +
                ", vehicleSubcategories=" + vehicleSubcategories.stream().map(VehicleSubCategory::getVehicleSubcategoryId).collect(Collectors.toSet()) +
                '}';
    }
}
