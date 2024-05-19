package com.coungard.service.impl;

import com.coungard.model.WeightModel;
import com.coungard.entity.DeliveryOrder;
import com.coungard.mapper.DeliveryOrderMapper;
import com.coungard.mapper.WeightMapper;
import com.coungard.model.AddressModel;
import com.coungard.model.DeliveryOrderModel;
import com.coungard.model.DeliveryOrderStatus;
import com.coungard.model.request.CreateDeliveryOrderRequest;
import com.coungard.repository.OrderRepository;
import com.coungard.repository.WeightRepository;
import com.coungard.service.OrderService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryOrderService implements OrderService {

  private final OrderRepository orderRepository;
  private final DeliveryOrderMapper orderMapper = DeliveryOrderMapper.INSTANCE;
  private final WeightMapper weightMapper = WeightMapper.INSTANCE;

  private final WeightRepository weightRepository;

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
  public List<DeliveryOrderModel> getOwnOrders() {
    String email = definePrincipal();
    List<DeliveryOrder> deliveryOrders = orderRepository.defineAllOrdersByEmail(email);
    return orderMapper.toDeliveryOrderModelList(deliveryOrders);
  }

  @Override
  public DeliveryOrderModel changeDestination(Long id, AddressModel destination) {
    DeliveryOrder deliveryOrder = orderRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Delivery order by id=" + id + " not found!"));

    deliveryOrder.setDestination(orderMapper.toAddressFromAddressModel(destination));
    DeliveryOrder saved = orderRepository.save(deliveryOrder);
    return orderMapper.toDeliveryOrderModel(saved);
  }

  @Override
  public Long deleteOrder(Long id) {
    return Optional.of(orderRepository.findById(id))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .filter(order -> order.getStatus().equals(DeliveryOrderStatus.CREATED))
        .map(order -> {
          orderRepository.delete(order);
          return id;
        })
        .orElseThrow(() -> new EntityNotFoundException("Order not found or status is not CREATED"));
  }

  @Override
  public List<WeightModel> getWeightsList() {
    return weightRepository.findAll().stream()
        .map(weightMapper::toWeightDto)
        .collect(Collectors.toList());
  }

  private String definePrincipal() {
    return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }
}
