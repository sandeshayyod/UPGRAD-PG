package com.upgrad.orderservice.service;

import com.upgrad.commons.model.User;
import com.upgrad.orderservice.model.OrderException;

public interface OrderService {

    String placeOrder(User user, int itemId) throws OrderException;
}
