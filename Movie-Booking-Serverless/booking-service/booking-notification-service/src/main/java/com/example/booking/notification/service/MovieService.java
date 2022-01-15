package com.example.booking.notification.service;

import com.example.booking.notification.dto.MovieDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class MovieService {

    private OkHttpClient okHttpClient = new OkHttpClient();
    private ObjectMapper objectMapper = new ObjectMapper();

    public MovieDto getMovieById(String movieId) throws IOException {
        Request request = new Request.Builder()
                .url("https://n3fkf7xc3l.execute-api.us-east-1.amazonaws.com/PROD/movie/"+movieId)
                .build();
        Response response = okHttpClient.newCall(request).execute();

        if (response.code() != 200) {
            return null;
        } else {
            String movieJson = response.body().string();
            return objectMapper.readValue(movieJson, MovieDto.class);
        }

    }
}
