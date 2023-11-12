package com.coungard.model.request;

import com.coungard.model.CourierStatus;
import com.coungard.model.CourierType;
import io.swagger.annotations.ApiModel;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

//@Builder
@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Register Courier Request")
public class RegisterCourierRequest extends SignUpRequest {

  @NotNull(message = "courier type is mandatory")
  private CourierType type;
  @NotNull(message = "courier status is mandatory")
  private CourierStatus status;
}
