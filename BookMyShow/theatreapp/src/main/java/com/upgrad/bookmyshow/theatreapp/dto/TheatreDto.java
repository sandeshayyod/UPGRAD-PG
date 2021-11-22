package com.upgrad.bookmyshow.theatreapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TheatreDto {
    private long theatreId;
    private String theatreName;
    private String location;
    private long noOfSeats;
    private long movieId;
}
