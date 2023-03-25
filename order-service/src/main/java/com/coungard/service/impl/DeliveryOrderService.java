package com.coungard.service.impl;

import com.coungard.entity.DeliveryOrder;
import com.coungard.mapper.DeliveryOrderMapper;
import com.coungard.model.AddressModel;
import com.coungard.model.DeliveryOrderModel;
import com.coungard.model.DeliveryOrderStatus;
import com.coungard.model.request.CreateDeliveryOrderRequest;
import com.coungard.repository.OrderRepository;
import com.coungard.service.OrderService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryOrderService implements OrderService {

  private final OrderRepository orderRepository;
  private final DeliveryOrderMapper orderMapper = DeliveryOrderMapper.INSTANCE;

  @Override
  public DeliveryOrderModel createOrder(CreateDeliveryOrderRequest request) {
    String email = definePrincipal();
    LocalDateTime createdDate = LocalDateTime.now();

    DeliveryOrder deliveryOrder = orderMapper.toDeliveryOrder(request)
        .withCreatedBy(email)
        .withCreatedDate(createdDate)
        .withStatus(DeliveryOrderStatus.CREATED);

    deliveryOrder.getParcels()
        .forEach(parcel -> {
          parcel.setCreatedBy(email);
          parcel.setCreatedDate(createdDate);
          parcel.setDeliveryOrder(deliveryOrder);
        });

    DeliveryOrder saved = orderRepository.save(deliveryOrder);
    return orderMapper.toDeliveryOrderModel(saved);
  }

  @Override
  public List<DeliveryOrderModel> getOwnOrders(DeliveryOrderStatus status) {
    String email = definePrincipal();
    List<DeliveryOrder> deliveryOrders = orderRepository.defineAllOrdersByEmailAndStatuses(email, status);
    return orderMapper.toDeliveryOrderModelList(deliveryOrders);
  }

  @Override
  public DeliveryOrderModel changeDestination(Long id, AddressModel destination) {
    DeliveryOrder deliveryOrder = orderRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Delivery order by id=" + id + " not found!"));

    deliveryOrder.setDestination(orderMapper.toAddressFromAddressModel(destination));
    DeliveryOrder saved = orderRepository.save(deliveryOrder);
    return orderMapper.toDeliveryOrderModel(saved);
  }

  private String definePrincipal() {
    return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }
}
