package com.upgrad.commons.model.request;

import com.upgrad.commons.model.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderRequest {

    private final User user;
    private final int itemId;
}
