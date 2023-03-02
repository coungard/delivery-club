package com.coungard.service.impl;

import com.coungard.model.request.CreateDeliveryOrderRequest;
import com.coungard.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class DeliveryOrderService implements OrderService {

  @Override
  public String createOrder(CreateDeliveryOrderRequest request) {
    return null;
  }
}
