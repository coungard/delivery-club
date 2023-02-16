package com.coungard.model;

import lombok.Data;

@Data
public class DeliveryOrderResponse {

  private Long id;
  private String username;
  private DeliveryOrderStatus deliveryOrderStatus;
}
