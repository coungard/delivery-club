package com.coungard.service;

import com.coungard.model.DeliveryOrderModel;
import com.coungard.model.request.CreateDeliveryOrderRequest;

public interface OrderService {

  DeliveryOrderModel createOrder(CreateDeliveryOrderRequest request);
}
