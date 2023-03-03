package com.coungard.model.request;

import com.coungard.model.AddressModel;
import com.coungard.model.CreateParcelModel;
import io.swagger.annotations.ApiModel;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Create delivery order request")
public class CreateDeliveryOrderRequest {

  @NotNull(message = "parcels is mandatory")
  @NotEmpty(message = "parcels must have at least 1 item")
  private List<CreateParcelModel> parcels;

  @NotNull(message = "address is mandatory")
  AddressModel address;
}
