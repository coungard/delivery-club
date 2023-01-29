package com.coungard.model.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SignUpRequest {

  private String email;
  private String name;
  private String password;
}
