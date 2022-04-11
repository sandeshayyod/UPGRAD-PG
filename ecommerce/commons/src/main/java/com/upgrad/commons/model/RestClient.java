package com.upgrad.commons.model;

import com.upgrad.commons.model.request.HttpRequest;
import com.upgrad.commons.model.response.HttpResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
public final class RestClient {

    private static final HttpHeaders HTTP_HEADERS = new HttpHeaders();

    static {
        HTTP_HEADERS.add("Content-Type", "application/json");
        HTTP_HEADERS.add("Accept", "*/*");
    }

    private final RestTemplate template;

    public HttpResponse get(final HttpRequest request) {
        final HttpEntity<String> requestEntity = new HttpEntity<>("", HTTP_HEADERS);
        final ResponseEntity<String> responseEntity = template
                .exchange(request.getUri(), HttpMethod.GET, requestEntity, String.class);
        return HttpResponse.builder().body(responseEntity.getBody())
                .status(responseEntity.getStatusCode()).build();
    }

    public HttpResponse post(final HttpRequest request) {
        final HttpEntity<String> requestEntity = new HttpEntity<>(request.getBody(), HTTP_HEADERS);
        final ResponseEntity<String> responseEntity = template
                .exchange(request.getUri(), HttpMethod.POST, requestEntity, String.class);
        return HttpResponse.builder().body(responseEntity.getBody())
                .status(responseEntity.getStatusCode()).build();
    }
}