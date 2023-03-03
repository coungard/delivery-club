package com.coungard.model;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@ApiModel(value = "Delivery recipient address")
public class AddressModel {

  private String city;
  private String district;
  private String receiverName;
  private String receiveSurname;
  @NotNull(message = "phone is mandatory")
  private String receiverPhone;
}
