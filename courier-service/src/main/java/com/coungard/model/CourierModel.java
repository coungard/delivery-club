package com.coungard.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourierModel {

  private String email;
  private String name;
  private CourierType type;
  private CourierStatus status;
}
