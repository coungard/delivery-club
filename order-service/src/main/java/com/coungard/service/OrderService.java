package com.coungard.service;

import com.coungard.model.AddressModel;
import com.coungard.model.DeliveryOrderModel;
import com.coungard.model.WeightModel;
import com.coungard.model.request.CreateDeliveryOrderRequest;
import java.util.List;

public interface OrderService {

  DeliveryOrderModel createOrder(CreateDeliveryOrderRequest request);

  List<DeliveryOrderModel> getOwnOrders();

  DeliveryOrderModel changeDestination(Long id, AddressModel destination);

  Long deleteOrder(Long id);

  List<WeightModel> getWeightsList();
}
