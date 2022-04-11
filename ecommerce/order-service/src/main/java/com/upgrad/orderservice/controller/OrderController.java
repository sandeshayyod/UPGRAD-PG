package com.upgrad.orderservice.controller;

import com.upgrad.commons.model.Status;
import com.upgrad.commons.model.request.OrderRequest;
import com.upgrad.commons.model.response.OrderResponse;
import com.upgrad.orderservice.model.OrderException;
import com.upgrad.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public final class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;


    @PostMapping("/place-order")
    public OrderResponse placeOrder(@RequestBody final OrderRequest orderRequest) {

        try {
            LOGGER.info("Order placed for Item={} by User={}", orderRequest.getItemId(), orderRequest.getUser().getId());
            return OrderResponse.builder()
                    .orderId(orderService.placeOrder(orderRequest.getUser(), orderRequest.getItemId()))
                    .itemId(orderRequest.getItemId())
                    .orderStatus(Status.SUCCESSFUL)
                    .build();
        } catch (final OrderException e) {
            LOGGER.error("Exception while placing the order for Item={} by User={}",
                    orderRequest.getItemId(), orderRequest.getUser().getId(), e);
            return OrderResponse.builder()
                    .itemId(orderRequest.getItemId())
                    .orderStatus(Status.FAILED)
                    .build();
        }
    }
}
