package com.coungard.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "Parcel Weight")
public class WeightModel {

  private ParcelType type;
  private double minWeight;
  private double maxWeight;
}
