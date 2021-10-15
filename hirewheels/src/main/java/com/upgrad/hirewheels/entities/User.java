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
public class User {

    @Id
    @Column(length = 10)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column(length = 50, nullable = false)
    @NonNull
    private String firstName;

    @Column(length = 50)
    @NonNull
    private String lastName;

    @Column(length = 50, nullable = false)
    @NonNull
    private String password;

    @Column(length = 50, nullable = false, unique = true)
    @NonNull
    private String email;

    @Column(length = 10, nullable = false, unique = true, columnDefinition = "CHAR")
    @NonNull
    private String mobileNo;

    @Column(length = 10, precision = 2)
    @NonNull
    private float walletMoney;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    @NonNull
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Booking> bookings;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", walletMoney=" + walletMoney +
                ", role=" + role +
                ", bookings=" + bookings.stream().map(Booking::getBookingId).collect(Collectors.toSet()) +
                '}';
    }
}
