package com.coungard.entity;

import com.coungard.model.DeliveryOrderStatus;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "delivery-order")
public class DeliveryOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private DeliveryOrderStatus deliveryOrderStatus;

  private String product;

  private String owner;
  private String executor;
  private LocalDateTime createdAt;
}
