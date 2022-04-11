package com.upgrad.inventoryservice.controller;

import com.upgrad.commons.model.Error;
import com.upgrad.commons.model.response.ItemResponse;
import com.upgrad.inventoryservice.model.InventoryException;
import com.upgrad.inventoryservice.service.InventoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/inventory")
public final class InventoryController {


    private final InventoryService inventoryService;


    @GetMapping("/get-item/{item-id}")
    public ItemResponse getItem(@PathVariable(value = "item-id") final int itemId) {
        try {
            log.info("getting item for itemId={}", itemId);
            return ItemResponse.builder()
                    .itemPrice(inventoryService.getPrice(itemId))
                    .available(true)
                    .build();
        } catch (final InventoryException e) {
            log.error("exception while getting item for itemId={}", itemId, e);
            return ItemResponse.builder()
                    .available(false)
                    .error(Error.builder().message(e.getMessage()).build())
                    .build();
        }
    }
}
