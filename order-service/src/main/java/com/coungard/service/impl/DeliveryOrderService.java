package com.coungard.service.impl;

import com.coungard.entity.DeliveryOrder;
import com.coungard.entity.Parcel;
import com.coungard.mapper.DeliveryOrderMapper;
import com.coungard.model.DeliveryOrderModel;
import com.coungard.model.DeliveryOrderStatus;
import com.coungard.model.request.CreateDeliveryOrderRequest;
import com.coungard.repository.OrderRepository;
import com.coungard.repository.ParcelRepository;
import com.coungard.service.OrderService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryOrderService implements OrderService {

  private final OrderRepository orderRepository;
  private final ParcelRepository parcelRepository;
  private final DeliveryOrderMapper orderMapper = DeliveryOrderMapper.INSTANCE;

  @Override
  public DeliveryOrderModel createOrder(CreateDeliveryOrderRequest request) {
    DeliveryOrder deliveryOrder = orderMapper.toDeliveryOrder(request)
        .withStatus(DeliveryOrderStatus.CREATED);
    DeliveryOrder saved = orderRepository.save(deliveryOrder);
    Set<Parcel> parcels = deliveryOrder.getParcels();
    parcelRepository.saveAll(parcels);
    return orderMapper.toDeliveryOrderModel(saved);
  }
}
