package com.upgrad.bookmyshow.userapp.service;

public interface MovieBookingService {

    boolean bookMovie(long movieId, long theatreId, long seats);

    boolean cancelMovieBooking(long movieId, long theatreId, long seats);

    boolean checkAvailable(long movieId, long theatreId, long seats);
}
