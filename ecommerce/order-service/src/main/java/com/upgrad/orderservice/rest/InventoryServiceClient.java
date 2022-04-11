package com.upgrad.orderservice.rest;

import com.upgrad.commons.model.response.ItemResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public final class InventoryServiceClient {

    private static final String SERVICE_URL = "http://inventory-service:8070/inventory";

    private final RestTemplate restTemplate;

    public ItemResponse getItem(final int itemId) {
        final ResponseEntity<ItemResponse> responseEntity = restTemplate
                .getForEntity(SERVICE_URL + "/get-item/" + itemId, ItemResponse.class);
        return responseEntity.getBody();
    }
}
