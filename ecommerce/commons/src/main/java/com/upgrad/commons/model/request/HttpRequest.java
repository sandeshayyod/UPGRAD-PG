package com.upgrad.commons.model.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HttpRequest {

    private final String uri;
    private final String body;
}
