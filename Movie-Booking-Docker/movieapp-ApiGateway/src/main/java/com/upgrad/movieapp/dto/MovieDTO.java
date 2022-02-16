package com.upgrad.movieapp.dto;

import java.time.LocalDateTime;


public class MovieDTO {

  private int movieId ;

  private String movieName ;

  private String movieDescription ;

  private LocalDateTime releaseDate;

  private int duration;

  private String coverPhotoUrl;

  private String trailerUrl;

  public int getMovieId() {
    return movieId;
  }

  public void setMovieId(int movieId) {
    this.movieId = movieId;
  }

  public String getMovieName() {
    return movieName;
  }

  public void setMovieName(String movieName) {
    this.movieName = movieName;
  }

  public String getMovieDescription() {
    return movieDescription;
  }

  public void setMovieDescription(String movieDescription) {
    this.movieDescription = movieDescription;
  }

  public LocalDateTime getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(LocalDateTime releaseDate) {
    this.releaseDate = releaseDate;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public String getCoverPhotoUrl() {
    return coverPhotoUrl;
  }

  public void setCoverPhotoUrl(String coverPhotoUrl) {
    this.coverPhotoUrl = coverPhotoUrl;
  }

  public String getTrailerUrl() {
    return trailerUrl;
  }

  public void setTrailerUrl(String trailerUrl) {
    this.trailerUrl = trailerUrl;
  }
}
