package com.coungard.model.request;

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
public class LoginRequest {

  @Email
  @NotBlank
  @Size(max = 40)
  private String email;
  @Size(min = 6, max = 20)
  @NotBlank
  private String password;
}
