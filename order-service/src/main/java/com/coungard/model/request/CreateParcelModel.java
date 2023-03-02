package com.coungard.model.request;

import com.coungard.model.ParcelType;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateParcelModel {

  @NotNull(message = "type is mandatory")
  private ParcelType type;
  @NotNull(message = "weight is mandatory")
  private Double weight;
}
