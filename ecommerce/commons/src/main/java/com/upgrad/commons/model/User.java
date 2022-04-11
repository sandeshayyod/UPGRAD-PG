package com.upgrad.commons.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

    private final String id;
    private final String name;
    private final String email;
}
