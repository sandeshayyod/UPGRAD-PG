package com.upgrad.movieapp.dto;

import java.time.LocalDateTime;


public class TheatreDTO {

 private int theatreId;
 private int movieId;

 public int getTheatreId() {
  return theatreId;
 }

 public void setTheatreId(int theatreId) {
  this.theatreId = theatreId;
 }

 public int getMovieId() {
  return movieId;
 }

 public void setMovieId(int movieId) {
  this.movieId = movieId;
 }
}
