package com.upgrad.bookmyshow.theatreapp.dao;

import com.upgrad.bookmyshow.theatreapp.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreDao extends JpaRepository<Theatre, Long> {
}
