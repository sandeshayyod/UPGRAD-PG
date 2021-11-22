package com.upgrad.bookmyshow.theatreapp.controller;

import com.upgrad.bookmyshow.theatreapp.dto.TheatreDto;
import com.upgrad.bookmyshow.theatreapp.service.TheatreService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Log4j2
@RestController
@RequestMapping(value = "/theatreapp/v1")
public class TheatreController {

    TheatreService theatreService;

    @Autowired
    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @GetMapping(value = {"/hello/{name}", "/hello", "/"})
    public String sayHello(@PathVariable(name = "name", required = false) String name) {
        String welcome = "Welcome" + (name == null ? "" : " " + name) + "!";
        return "Hi! the Theatre is live and able to respond. " + welcome;
    }

    @GetMapping(value = "/theatres/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TheatreDto> getMovie(@PathVariable(name = "id") String theatreId) {
        TheatreDto theatreDto = theatreService.getTheatre(Long. parseLong(theatreId));
        return new ResponseEntity<>(theatreDto, HttpStatus.CREATED);
    }

    @PostMapping(value = "/theatres", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TheatreDto> registerTheatre(@RequestBody TheatreDto theatreDto) {
        TheatreDto savedTheatreDto = theatreService.saveTheatre(theatreDto);
        return new ResponseEntity<>(savedTheatreDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/theatres", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TheatreDto> updateTheatre(@RequestBody TheatreDto theatreDto) {
        TheatreDto updatedTheatreDto = theatreService.updateTheatre(theatreDto);
        return new ResponseEntity<>(updatedTheatreDto, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/theatres/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TheatreDto> deleteMovie(@PathVariable(name = "id") String theatreId) {
        TheatreDto deletedTheatreDto = theatreService.deleteTheatre(Long.parseLong(theatreId));
        return new ResponseEntity<>(deletedTheatreDto, HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/checkAvailable", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> checkAvailable(@RequestParam(value = "theatreId") String theatreId,
                                                  @RequestParam(value = "movieId") String movieId,
                                                  @RequestParam(value = "seats") String seats) {
        boolean isAvailable = theatreService.checkAvailable(Long.parseLong(theatreId), Long.parseLong(movieId), Long.parseLong(seats));
        if (!isAvailable) {
            log.info("Theatre with " + theatreId + " and movie with " + movieId + " not available for bookings");
        }
        return new ResponseEntity<>(isAvailable, HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/bookSeat", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> bookTheatreSeat(@RequestParam(value = "theatreId") String theatreId,
                                                 @RequestParam(value = "movieId") String movieId,
                                                 @RequestParam(value = "seats") String seats) {
        boolean isBooked = theatreService.bookSeat(Long.parseLong(theatreId), Long.parseLong(movieId),
                Long.parseLong(seats));
        return new ResponseEntity<>(isBooked, HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/cancelSeat", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> cancelTheatreSeat(@RequestParam(value = "theatreId") String theatreId,
                                                 @RequestParam(value = "movieId") String movieId,
                                                 @RequestParam(value = "seats") String seats) {
        boolean isBooked = theatreService.cancelSeat(Long.parseLong(theatreId), Long.parseLong(movieId),
                Long.parseLong(seats));
        return new ResponseEntity<>(isBooked, HttpStatus.ACCEPTED);
    }
}
