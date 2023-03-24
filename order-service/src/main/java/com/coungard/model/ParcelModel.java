package com.coungard.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "Parcel Model")
public class ParcelModel {

  private Long id;
  private ParcelType type;
  private Double weight;
}
