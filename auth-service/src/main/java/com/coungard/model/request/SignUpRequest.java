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
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SignUp Request information")
public class SignUpRequest {

  @NotBlank
  @Size(max = 40)
  @Email
  @ApiModelProperty(value = "User Email")
  private String email;

  @NotBlank
  @Size(min = 4, max = 40)
  @ApiModelProperty(value = "User Name")
  private String name;

  @NotBlank
  @Size(min = 6, max = 20)
  @ApiModelProperty(value = "User Password")
  private String password;
}
