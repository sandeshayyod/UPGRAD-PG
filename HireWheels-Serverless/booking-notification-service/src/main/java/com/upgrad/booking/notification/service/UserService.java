package com.upgrad.booking.notification.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upgrad.booking.notification.dto.UserDTO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class UserService {

    private OkHttpClient okHttpClient = new OkHttpClient();
    private ObjectMapper objectMapper = new ObjectMapper();

    public UserDTO getUserById(String userId) throws IOException {
        Request request = new Request.Builder()
                .url("https://dcmd5ctdl2.execute-api.us-east-1.amazonaws.com/PROD/users/"+userId)
                .build();
        Response response = okHttpClient.newCall(request).execute();

        if (response.code() != 200) {
            return null;
        } else {
            String userJson = response.body().string();
            return objectMapper.readValue(userJson, UserDTO.class);
        }
    }

}
