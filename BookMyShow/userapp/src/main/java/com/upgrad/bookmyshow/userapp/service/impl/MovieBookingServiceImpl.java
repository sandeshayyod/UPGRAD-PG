package com.upgrad.bookmyshow.userapp.service.impl;

import com.upgrad.bookmyshow.userapp.dao.MovieBookingDao;
import com.upgrad.bookmyshow.userapp.feign.TheatreServiceClient;
import com.upgrad.bookmyshow.userapp.service.MovieBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class MovieBookingServiceImpl implements MovieBookingService {

    @Autowired
    private MovieBookingDao movieBookingDao;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${theatreApp.bookSeat.url}")
    private String theatreAppBookMovieUrl;

    @Value("${theatreApp.cancelSeat.url}")
    private String theatreAppCancelSeatUrl;

    @Value("${theatreApp.checkAvailable.url}")
    private String theatreAppCheckAvailableUrl;

    @Autowired
    private TheatreServiceClient theatreServiceClient;


    @Override
    public boolean bookMovie(long movieId, long theatreId, long seats) {
        Map<String, String> theatreUriMap = new HashMap<>();
        theatreUriMap.put("theatreId", String.valueOf(theatreId));
        theatreUriMap.put("movieId", String.valueOf(movieId));
        theatreUriMap.put("seats", String.valueOf(seats));
        //final ResponseEntity<Boolean> responseEntity = restTemplate.exchange(theatreAppBookMovieUrl, HttpMethod.PUT, HttpEntity.EMPTY, Boolean.class, theatreUriMap);
        final ResponseEntity<Boolean> responseEntity = theatreServiceClient.bookTheatreSeat(String.valueOf(theatreId), String.valueOf(movieId), String.valueOf(seats));
        Boolean isBooked = responseEntity.getBody();
        if (isBooked == null) {
            return false;
        }
        return isBooked;
    }

    @Override
    public boolean cancelMovieBooking(long movieId, long theatreId, long seats) {
        Map<String, String> theatreUriMap = new HashMap<>();
        theatreUriMap.put("theatreId", String.valueOf(theatreId));
        theatreUriMap.put("movieId", String.valueOf(movieId));
        theatreUriMap.put("seats", String.valueOf(seats));
        //final ResponseEntity<Boolean> responseEntity = restTemplate.exchange(theatreAppCancelSeatUrl, HttpMethod.PUT, HttpEntity.EMPTY, Boolean.class, theatreUriMap);
        final ResponseEntity<Boolean> responseEntity = theatreServiceClient.cancelTheatreSeat(String.valueOf(theatreId), String.valueOf(movieId), String.valueOf(seats));
        Boolean isCancelled = responseEntity.getBody();
        if (isCancelled == null) {
            return false;
        }
        return isCancelled;
    }

    @Override
    public boolean checkAvailable(long movieId, long theatreId, long seats) {
        Map<String, String> theatreUriMap = new HashMap<>();
        theatreUriMap.put("theatreId", String.valueOf(theatreId));
        theatreUriMap.put("movieId", String.valueOf(movieId));
        theatreUriMap.put("seats", String.valueOf(seats));
        final Boolean isAvailable = restTemplate.getForObject(theatreAppCheckAvailableUrl, Boolean.class, theatreUriMap);
        if (isAvailable == null) {
            return false;
        }
        return isAvailable;
    }
}
