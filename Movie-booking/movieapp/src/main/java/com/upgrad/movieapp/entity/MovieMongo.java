package com.upgrad.movieapp.entity;

import lombok.*;
import org.springframework.context.annotation.Profile;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieMongo {

    @Id
    private int movieId;

    private String movieName;

    private String movieDescription;

    private LocalDateTime releaseDate;

    private int duration;

    private String coverPhotoUrl;

    private String trailerUrl;

}
