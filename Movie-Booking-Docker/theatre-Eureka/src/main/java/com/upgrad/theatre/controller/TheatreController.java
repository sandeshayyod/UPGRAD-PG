package com.upgrad.theatre.controller;

import com.upgrad.theatre.dto.TheatreDTO;
import com.upgrad.theatre.entities.Theatre;
import com.upgrad.theatre.services.TheatreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/theatre_app/v1")
public class TheatreController {

    @Autowired
    TheatreService theatreService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping(value = "/theatres/{theatreId}/movie/{movieId}",produces = "application/json")
    public ResponseEntity getTheatreFromId(@PathVariable(name = "theatreId") int theatreId,
                                           @PathVariable(name = "movieId") int movieId){
        Theatre responseTheatre = theatreService.getTheatreDetails(theatreId,movieId);

        if(responseTheatre!=null) {
            TheatreDTO responseTheatreDTO = modelMapper.map(responseTheatre,TheatreDTO.class);
            return new ResponseEntity(responseTheatreDTO, HttpStatus.OK);
        }else{
            return null;
        }
    }
}
