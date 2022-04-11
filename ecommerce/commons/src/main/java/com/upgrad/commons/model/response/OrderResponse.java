package com.upgrad.commons.model.response;

import com.upgrad.commons.model.Status;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderResponse {

    private final String orderId;
    private final int itemId;
    private final Status orderStatus;
}
