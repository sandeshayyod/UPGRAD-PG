package com.upgrad.inventoryservice.service;

import com.upgrad.inventoryservice.model.InventoryException;
import org.springframework.stereotype.Service;

@Service
public final class InventoryServiceImpl implements InventoryService {

    @Override
    public double getPrice(final int itemId) throws InventoryException {
        if (itemId < 500) {
            return Math.random() * 1000;
        }
        throw new InventoryException("Item not available");
    }
}
