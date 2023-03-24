package com.coungard.service;

import com.coungard.model.DeliveryOrderModel;
import com.coungard.model.DeliveryOrderStatus;
import com.coungard.model.request.CreateDeliveryOrderRequest;
import java.util.List;

public interface OrderService {

  DeliveryOrderModel createOrder(CreateDeliveryOrderRequest request);

  List<DeliveryOrderModel> getOwnOrders(DeliveryOrderStatus status);
}
