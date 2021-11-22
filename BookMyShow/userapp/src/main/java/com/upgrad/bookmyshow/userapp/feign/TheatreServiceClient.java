package com.upgrad.bookmyshow.userapp.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "theatre-service")
public interface TheatreServiceClient {

    @RequestMapping(value = "${theatreapp.bookseat.mapping}", method = RequestMethod.PUT, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Boolean> bookTheatreSeat(@RequestParam(value = "theatreId") String theatreId,
                                            @RequestParam(value = "movieId") String movieId,
                                            @RequestParam(value = "seats") String seats);

    @RequestMapping(value = "${theatreapp.cancelseat.mapping}", method = RequestMethod.PUT, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Boolean> cancelTheatreSeat(@RequestParam(value = "theatreId") String theatreId,
                                              @RequestParam(value = "movieId") String movieId,
                                              @RequestParam(value = "seats") String seats);


}
