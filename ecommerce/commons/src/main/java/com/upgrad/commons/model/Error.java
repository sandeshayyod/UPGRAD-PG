package com.upgrad.commons.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Error {

    private final int code;
    private final String message;
}
