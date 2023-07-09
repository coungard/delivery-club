package com.coungard.model;

import io.swagger.annotations.ApiModel;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@ApiModel(value = "Create Parcel model, when we creating delivery order")
public class CreateParcelModel {

  @NotNull(message = "type is mandatory")
  private ParcelType type;
  @NotNull(message = "weight is mandatory")
  @DecimalMin(value = "0.1", message = "Посылка не может весить меньше, чем 0.1 кг")
  @DecimalMax(value = "1000", message = "Максимальный вес посылки - 1 тонна")
  @Digits(integer = 6, fraction = 2)
  private BigDecimal weight;
}
