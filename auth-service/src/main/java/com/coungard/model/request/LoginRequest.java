package com.coungard.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Login Request credentials")
public class LoginRequest {

  @Email
  @NotBlank
  @Size(max = 40)
  @ApiModelProperty(value = "User Email")
  private String email;
  @Size(min = 6, max = 20)
  @NotBlank
  @ApiModelProperty(value = "User Password")
  private String password;
}
