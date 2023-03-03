package com.coungard.model;

import lombok.Data;

@Data
public class DeliveryOrderModel {

  private Long id;
  private DeliveryOrderStatus status;
  private AddressModel address;
}
