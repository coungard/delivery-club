package com.coungard.model;

import io.swagger.annotations.ApiModel;
import java.util.List;
import lombok.Data;

@Data
@ApiModel(value = "Parcel Model")
public class DeliveryOrderModel {

  private Long id;
  private DeliveryOrderStatus status;
  private AddressModel destination;
  private List<ParcelModel> parcels;
}
