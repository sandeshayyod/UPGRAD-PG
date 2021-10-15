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
public class City {

    @Id
    @Column(length = 10)
    @NonNull
    private int cityId;

    @Column(length = 50, nullable = false)
    @NonNull
    private String cityName;

    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Location> locations;

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", locations=" + locations.stream().map(Location::getLocationId).collect(Collectors.toSet()) +
                '}';
    }
}
