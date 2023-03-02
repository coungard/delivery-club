package com.coungard.service;

import com.coungard.model.request.CreateDeliveryOrderRequest;

public interface OrderService {

  String createOrder(CreateDeliveryOrderRequest request);
}
