package com.coungard.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {

  private Long id;
  private String token;
  private String roleName;
}
