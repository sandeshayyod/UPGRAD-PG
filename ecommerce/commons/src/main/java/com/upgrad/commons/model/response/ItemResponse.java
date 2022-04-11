package com.upgrad.commons.model.response;

import com.upgrad.commons.model.Error;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemResponse {

    private final boolean available;
    private final double itemPrice;
    private final Error error;
}
