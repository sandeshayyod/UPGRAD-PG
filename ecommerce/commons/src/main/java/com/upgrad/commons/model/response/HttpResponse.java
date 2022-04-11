package com.upgrad.commons.model.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class HttpResponse {

    private final String body;
    private final HttpStatus status;
}
