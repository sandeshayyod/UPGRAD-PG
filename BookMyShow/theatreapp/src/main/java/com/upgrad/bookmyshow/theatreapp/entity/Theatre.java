package com.upgrad.bookmyshow.theatreapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Theatre {
    @Id
    private long theatreId;
    private String theatreName;
    private String location;
    private long noOfSeats;
    private long movieId;
}
