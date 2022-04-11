package com.upgrad.inventoryservice.service;

import com.upgrad.inventoryservice.model.InventoryException;

public interface InventoryService {

    double getPrice(int itemId) throws InventoryException;
}
