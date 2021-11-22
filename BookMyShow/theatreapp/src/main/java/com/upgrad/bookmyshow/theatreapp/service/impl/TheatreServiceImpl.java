package com.upgrad.bookmyshow.theatreapp.service.impl;

import com.upgrad.bookmyshow.theatreapp.dao.TheatreDao;
import com.upgrad.bookmyshow.theatreapp.dto.TheatreDto;
import com.upgrad.bookmyshow.theatreapp.entity.Theatre;
import com.upgrad.bookmyshow.theatreapp.exception.TheatreNotFoundException;
import com.upgrad.bookmyshow.theatreapp.service.TheatreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TheatreServiceImpl implements TheatreService {

    private final ModelMapper modelMapper;
    private final TheatreDao theatreDao;

    @Autowired
    public TheatreServiceImpl(ModelMapper modelMapper, TheatreDao theatreDao) {
        this.modelMapper = modelMapper;
        this.theatreDao = theatreDao;
    }

    @Override
    public TheatreDto getTheatre(long id) {
        Optional<Theatre> theatreOptional = theatreDao.findById(id);
        Theatre theatre = theatreOptional.orElseThrow(() -> new TheatreNotFoundException("Theatre with " + id + " not found"));
        return modelMapper.map(theatre, TheatreDto.class);
    }

    @Override
    public TheatreDto saveTheatre(TheatreDto theatreDto) {
        Theatre theatre = modelMapper.map(theatreDto, Theatre.class);
        theatre = theatreDao.save(theatre);
        return modelMapper.map(theatre, TheatreDto.class);
    }

    @Override
    public TheatreDto updateTheatre(TheatreDto theatreDto) {
        Optional<Theatre> theatreOptional = theatreDao.findById(theatreDto.getTheatreId());
        Theatre updatedTheatre = theatreOptional.map(
                theatre -> {
                    theatre.setTheatreName(theatreDto.getTheatreName());
                    theatre.setLocation(theatreDto.getLocation());
                    theatre.setNoOfSeats(theatreDto.getNoOfSeats());
                    theatre.setMovieId(theatreDto.getMovieId());
                    theatre.setTheatreName(theatreDto.getTheatreName());
                    return theatre;
                }
        ).orElseThrow(() -> new TheatreNotFoundException("Theatre with " + theatreDto.getTheatreId() + " not found"));
        updatedTheatre = theatreDao.save(updatedTheatre);
        return modelMapper.map(updatedTheatre, TheatreDto.class);
    }

    @Override
    public TheatreDto deleteTheatre(long theatreId) {
        Optional<Theatre> theatreOptional = theatreDao.findById(theatreId);
        Theatre deletedTheatre = theatreOptional.map(
                theatre -> {
                    theatreDao.delete(theatre);
                    return theatre;
                }
        ).orElseThrow(() -> new TheatreNotFoundException("Theatre with " + theatreId + " not found"));
        return modelMapper.map(deletedTheatre, TheatreDto.class);
    }

    @Override
    public boolean bookSeat(long theatreId, long movieId, long seats) {
        Optional<Theatre> theatreOptional = theatreDao.findById(theatreId);
        theatreOptional = theatreOptional
                .filter(theatre -> theatre.getMovieId() == movieId)
                .filter(theatre -> theatre.getNoOfSeats() > seats)
                .map(theatre -> {
                    theatre.setNoOfSeats(theatre.getNoOfSeats() - seats);
                    return theatreDao.save(theatre);
                });
        return theatreOptional.isPresent();
    }

    @Override
    public boolean cancelSeat(long theatreId, long movieId, long seats) {
        Optional<Theatre> theatreOptional = theatreDao.findById(theatreId);
        theatreOptional = theatreOptional
                .filter(theatre -> theatre.getMovieId() == movieId)
                .map(theatre -> {
                    theatre.setNoOfSeats(theatre.getNoOfSeats() + seats);
                    return theatreDao.save(theatre);
                });
        return theatreOptional.isPresent();
    }

    @Override
    public boolean checkAvailable(long theatreId, long movieId, long seats) {
        Optional<Theatre> theatreOptional = theatreDao.findById(theatreId);
        return theatreOptional
                .filter(theatre -> theatre.getMovieId() == movieId)
                .filter(theatre -> theatre.getNoOfSeats() > seats)
                .isPresent();
    }
}
