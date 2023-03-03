package com.coungard.model;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@ApiModel(value = "Parcel model")
public class CreateParcelModel {

  @NotNull(message = "type is mandatory")
  private ParcelType type;
  @NotNull(message = "weight is mandatory")
  private Double weight;
}
