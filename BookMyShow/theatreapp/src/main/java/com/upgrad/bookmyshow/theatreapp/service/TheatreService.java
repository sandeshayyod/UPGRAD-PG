package com.upgrad.bookmyshow.theatreapp.service;

import com.upgrad.bookmyshow.theatreapp.dto.TheatreDto;

public interface TheatreService {
    TheatreDto getTheatre(long id);

    TheatreDto saveTheatre(TheatreDto theatreDto);

    TheatreDto updateTheatre(TheatreDto theatreDto);

    TheatreDto deleteTheatre(long theatreId);

    boolean bookSeat(long theatreId, long movieId, long seats);

    boolean cancelSeat(long theatreId, long movieId, long seats);

    boolean checkAvailable(long theatreId, long movieId, long seats);
}
